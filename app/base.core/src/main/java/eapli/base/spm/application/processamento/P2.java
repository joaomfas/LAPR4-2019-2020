package eapli.base.spm.application.processamento;

import eapli.base.gestaodepositos.domain.CodigoDeposito;
import eapli.base.gestaodepositos.domain.Deposito;
import eapli.base.gestaodepositos.repository.DepositoRepository;
import eapli.base.gestaomateriasprimas.domain.CodigoInterno;
import eapli.base.gestaomateriasprimas.domain.MateriaPrima;
import eapli.base.gestaomateriasprimas.repositories.MateriaPrimaRepository;
import eapli.base.gestaomensagens.dominio.Mensagem;
import eapli.base.gestaoordensproducao.domain.Estorno;
import eapli.base.gestaoordensproducao.domain.ExecucaoOrdemProducao;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.spm.application.processamento.validacao.exceptions.DepositoInvalidoException;
import eapli.base.spm.application.processamento.validacao.exceptions.FormatacaoInvalidaException;
import eapli.base.spm.application.processamento.validacao.exceptions.ProdutoInvalidoException;
import eapli.base.spm.application.processamento.validacao.exceptions.QuantidadeInvalidaException;
import eapli.base.spm.application.processamento.validacao.exceptions.SPMException;
import java.util.Iterator;
import java.util.Optional;


public class P2 extends ProcessaMensagem {
    
    private final MateriaPrimaRepository materiaPrimaRepository = PersistenceContext.repositories().materiasPrimas();
    
    private final DepositoRepository depositoRepository = PersistenceContext.repositories().depositos();
    
    public P2(Mensagem mensagem) throws SPMException {
        super(mensagem);
        try {
            String campo = mensagem.rawData().toString().trim().split(";")[5];
        } catch (Exception e) {
            throw new FormatacaoInvalidaException();
        }
    }
    
    //P2 -> Estorno
    //P2 -> Máquina;TipoMsg;DataHora;Produto;Quantidade;Depósito
    @Override
    public boolean processa() throws SPMException {
        if(mensagem.obterOrdemProducao()==null) return false;
        
        Iterator<ExecucaoOrdemProducao> execucaoDaOrdensProducao = this.execOrdemProducaoRepository.execucaoDaOrdensProducao(mensagem.obterOrdemProducao()).iterator();
        if(execucaoDaOrdensProducao.hasNext()) {
           
            ExecucaoOrdemProducao execOrdProd = execucaoDaOrdensProducao.next();
            
            Estorno estorno = new Estorno(materiaPrima(), quantidade(), deposito(), buildCalendar());
            execOrdProd.registarEstorno(estorno);
            this.execOrdemProducaoRepository.save(execOrdProd);
            
        }
        
        return false;
    }
    
    private MateriaPrima materiaPrima() throws SPMException {
        Optional<MateriaPrima> mp = materiaPrimaRepository.ofIdentity(new CodigoInterno(campos[3].trim()));
        if(mp.isPresent())          
            return mp.get();
        else
            throw new ProdutoInvalidoException();
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
            return depositoRepository.ofIdentity(new CodigoDeposito(campos[5].trim())).get();
        } catch (Exception e) {
            throw new DepositoInvalidoException();
        }
    }
    
    
}
