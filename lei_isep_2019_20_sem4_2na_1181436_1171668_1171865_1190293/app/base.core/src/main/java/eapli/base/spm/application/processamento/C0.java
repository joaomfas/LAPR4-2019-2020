package eapli.base.spm.application.processamento;

import eapli.base.gestaodepositos.domain.CodigoDeposito;
import eapli.base.gestaodepositos.domain.Deposito;
import eapli.base.gestaodepositos.repository.DepositoRepository;
import eapli.base.gestaomateriasprimas.domain.*;
import eapli.base.gestaomateriasprimas.repositories.MateriaPrimaRepository;
import eapli.base.gestaomensagens.dominio.Mensagem;
import eapli.base.gestaoordensproducao.domain.Consumo;
import eapli.base.gestaoordensproducao.domain.ExecucaoOrdemProducao;
import eapli.base.gestaoprodutos.domain.CodigoFabrico;
import eapli.base.gestaoprodutos.domain.Produto;
import eapli.base.gestaoprodutos.repositories.ProdutoRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.spm.application.processamento.validacao.exceptions.CriacaoConsumoException;
import eapli.base.spm.application.processamento.validacao.exceptions.DepositoInvalidoException;
import eapli.base.spm.application.processamento.validacao.exceptions.FormatacaoInvalidaException;
import eapli.base.spm.application.processamento.validacao.exceptions.QuantidadeInvalidaException;
import eapli.base.spm.application.processamento.validacao.exceptions.SPMException;
import java.util.Iterator;
import java.util.Optional;


public class C0 extends ProcessaMensagem {
    
    private final ProdutoRepository produtoRepository = PersistenceContext.repositories().produtos();
    
    private final MateriaPrimaRepository materiaPrimaRpository = PersistenceContext.repositories().materiasPrimas();
    
    private final DepositoRepository depositoRepository = PersistenceContext.repositories().depositos();
    
    
    public C0(Mensagem mensagem) throws SPMException {
        super(mensagem);
        try {
            String campo = mensagem.rawData().toString().trim().split(";")[5];
        } catch (Exception e) {
            throw new FormatacaoInvalidaException();
        }
    }
    
    
    //C0 -> Consumo
    //C0 -> Máquina;TipoMsg;DataHora;Produto;Quantidade;Depósito
    //                                 MP   |    QT    |   DP
    @Override
    public boolean processa() throws SPMException {
        if(mensagem.obterOrdemProducao()==null) return false;
        
        Iterator<ExecucaoOrdemProducao> execucaoDaOrdensProducao = this.execOrdemProducaoRepository.execucaoDaOrdensProducao(mensagem.obterOrdemProducao()).iterator();
        if(execucaoDaOrdensProducao.hasNext()) {
            ExecucaoOrdemProducao execOrdProd = execucaoDaOrdensProducao.next();
            execOrdProd.registarConsumo(consumo());
            this.execOrdemProducaoRepository.save(execOrdProd);            
            return true;
        }
        return false;
    }
    
    
    private Consumo consumo() throws SPMException {
        Optional<MateriaPrima> mp = materiaPrimaRpository.ofIdentity(new CodigoInterno(campos[3].trim()));
        if(mp.isPresent())
            return new Consumo(mp.get(), quantidade(), deposito(), 0d, buildCalendar() );
        Optional<Produto> prd = produtoRepository.ofIdentity(new CodigoFabrico(campos[3].trim()));
        if(prd.isPresent()) {
            return new Consumo(prd.get(), quantidade(), deposito(), 0d, buildCalendar() );
        }
        throw new CriacaoConsumoException();
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
       
    private Deposito deposito() throws SPMException{
        try {
            Optional<Deposito> deposito = depositoRepository.ofIdentity(new CodigoDeposito(campos[5].trim()));
            if(!deposito.isPresent())
                throw new DepositoInvalidoException();
            return deposito.get();
        } catch (Exception ex) {
            throw new DepositoInvalidoException();
        }
    }
    
    
    
    
    
}
