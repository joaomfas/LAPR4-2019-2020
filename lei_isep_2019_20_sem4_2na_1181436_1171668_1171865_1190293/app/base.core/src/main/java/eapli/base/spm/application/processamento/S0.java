package eapli.base.spm.application.processamento;

import eapli.base.gestaomensagens.dominio.Mensagem;
import eapli.base.gestaoordensproducao.domain.CodigoOrdemProducao;
import eapli.base.gestaoordensproducao.domain.DataInicio;
import eapli.base.gestaoordensproducao.domain.EstadoOrdemProducao;
import eapli.base.gestaoordensproducao.domain.ExecucaoOrdemProducao;
import eapli.base.gestaoordensproducao.domain.OrdemProducao;
import eapli.base.gestaoordensproducao.repositories.OrdemProducaoRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.spm.application.processamento.validacao.exceptions.OrdemProducaoInvalidaException;
import eapli.base.spm.application.processamento.validacao.exceptions.SPMException;
import java.util.Iterator;

public class S0 extends ProcessaMensagem {
    
    private final OrdemProducaoRepository repository = PersistenceContext.repositories().ordensProducao();
    
    public S0(Mensagem mensagem) throws SPMException {
        super(mensagem);
    }
    
    public OrdemProducao ordemProducao() throws OrdemProducaoInvalidaException {
        try {
            return repository.ofIdentity(new CodigoOrdemProducao(campos[3])).get();
        } catch (Exception ex) {
            throw new OrdemProducaoInvalidaException();
        }
    }
    
    //S0 -> Inicio de Atividade
    //S0 -> Máquina;TipoMsg;DataHora;OrdemProducao
    //Nota : Só a primeira máquina conhece a ordem de producao.
    @Override
    public boolean processa() throws SPMException {
        if(mensagem.obterOrdemProducao()==null) return false;
        
        //execução ordem orid existe? | Inicio de atividade
        //Iterator<ExecucaoOrdemProducao> execucaoDaOrdensProducao = this.execOrdemProducaoRepository.execucaoDaOrdensProducao(mensagem.obterOrdemProducao()).iterator();
        if(primeiraMaquina()) { 
            ExecucaoOrdemProducao execOrdProd = new ExecucaoOrdemProducao(mensagem.obterOrdemProducao());
            execOrdProd.registarInicioAtividade(maquina(), buildCalendar());
            this.execOrdemProducaoRepository.save(execOrdProd);
            System.out.println("Ordem Produção " + execOrdProd.ordemProducao().identity() + " Iniciada na linha de produção [" + mensagem.obterLinhaProducao().identity() + "].");
            OrdemProducao ordProd = this.ordemProducaoRepository.ofIdentity(mensagem.obterOrdemProducao().identity()).get();
            ordProd.updateEstadoOrdemProducao(EstadoOrdemProducao.EXECUCAO);
            ordProd.updateDataInicio(new DataInicio(stringData()));
            this.ordemProducaoRepository.save(ordProd);
        } else {
            Iterator<ExecucaoOrdemProducao> execucaoDaOrdensProducao = this.execOrdemProducaoRepository.execucaoDaOrdensProducao(mensagem.obterOrdemProducao()).iterator();
            if(execucaoDaOrdensProducao.hasNext()) {
                ExecucaoOrdemProducao execOrdProd = execucaoDaOrdensProducao.next();
                execOrdProd.registarInicioAtividade(maquina(), buildCalendar());
                this.execOrdemProducaoRepository.save(execOrdProd);
            }
        }
        
        
        
        
        return false;
    }
    
}
