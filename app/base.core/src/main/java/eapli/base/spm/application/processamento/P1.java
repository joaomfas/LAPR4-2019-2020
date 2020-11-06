package eapli.base.spm.application.processamento;

import eapli.base.gestaodepositos.repository.DepositoRepository;
import eapli.base.gestaomensagens.dominio.Mensagem;
import eapli.base.gestaoordensproducao.domain.ExecucaoOrdemProducao;
import eapli.base.gestaoprodutos.domain.CodigoFabrico;
import eapli.base.gestaoprodutos.domain.Produto;
import eapli.base.gestaoprodutos.repositories.ProdutoRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.spm.application.processamento.validacao.exceptions.DataInvalidaException;
import eapli.base.spm.application.processamento.validacao.exceptions.FormatacaoInvalidaException;
import eapli.base.spm.application.processamento.validacao.exceptions.ProdutoInvalidoException;
import eapli.base.spm.application.processamento.validacao.exceptions.QuantidadeInvalidaException;
import eapli.base.spm.application.processamento.validacao.exceptions.SPMException;
import java.util.Iterator;


public class P1 extends ProcessaMensagem {
    
    private final ProdutoRepository produtoRepository = PersistenceContext.repositories().produtos();
    
    private final DepositoRepository depositoRepository = PersistenceContext.repositories().depositos();
    
    public P1(Mensagem mensagem) throws SPMException {
        super(mensagem);
        try {
            String campo = mensagem.rawData().toString().trim().split(";")[5];
        } catch (Exception e) {
            throw new FormatacaoInvalidaException();
        }
    }
    
    //P1 -> Produção
    //P1 -> Máquina;TipoMsg;DataHora;Produto;Quantidade;Lote
    @Override
    public boolean processa() throws SPMException {   
        if(mensagem.obterOrdemProducao()==null) return false;
        
        Iterator<ExecucaoOrdemProducao> execucaoDaOrdensProducao = this.execOrdemProducaoRepository.execucaoDaOrdensProducao(mensagem.obterOrdemProducao()).iterator();
        if(execucaoDaOrdensProducao.hasNext()) {
           
            ExecucaoOrdemProducao execOrdProd = execucaoDaOrdensProducao.next();
            
            execOrdProd.registarProducao(lote(), produto(), quantidade());
            
            this.execOrdemProducaoRepository.save(execOrdProd);
            
            return true;
            
        }
        
        return false;
    }
    
    
    private Produto produto() throws SPMException {
        try {
            return produtoRepository.ofIdentity(new CodigoFabrico(campos[3])).get();
        } catch (Exception e) {
            throw new ProdutoInvalidoException();
        }
    }

    private Double quantidade() throws SPMException {
        try {
            double qtd = Double.valueOf(campos[4]);
            if(qtd<0) throw new QuantidadeInvalidaException();
            return qtd;
        } catch (Exception ex) {
            throw new QuantidadeInvalidaException();
        }
    }
    
    private String lote() throws SPMException {
        try {
            return campos[5];
        } catch (Exception ex) {
            throw new DataInvalidaException();
        }
    }
    
}
