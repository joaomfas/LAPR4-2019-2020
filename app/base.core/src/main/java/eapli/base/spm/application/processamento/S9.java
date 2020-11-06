package eapli.base.spm.application.processamento;

import eapli.base.gestaomensagens.dominio.Mensagem;
import eapli.base.gestaoordensproducao.domain.CodigoOrdemProducao;
import eapli.base.gestaoordensproducao.domain.DataFim;
import eapli.base.gestaoordensproducao.domain.EstadoOrdemProducao;
import eapli.base.gestaoordensproducao.domain.ExecucaoOrdemProducao;
import eapli.base.gestaoordensproducao.domain.OrdemProducao;
import eapli.base.spm.application.processamento.validacao.exceptions.OrdemProducaoInvalidaException;
import eapli.base.spm.application.processamento.validacao.exceptions.SPMException;
import java.util.Iterator;

public class S9 extends ProcessaMensagem {
    
    public S9(Mensagem mensagem) throws SPMException {
        super(mensagem);
    }
    
    public OrdemProducao ordemProducao() throws SPMException {
        try {
            return this.ordemProducaoRepository.ofIdentity(new CodigoOrdemProducao(campos[3])).get();             
        } catch (Exception ex) {
            throw new OrdemProducaoInvalidaException();
        }
    }
    
    
    //S9 -> Fim de Atividade
    //S9 -> Máquina;TipoMsg;DataHora;OrdemProducao
    //Nota : Só a primeira máquina conhece a ordem de producao.
    @Override
    public boolean processa() throws SPMException {
        if(mensagem.obterOrdemProducao()==null) return false;
        //execução ordem orid existe? | Inicio de atividade
        Iterator<ExecucaoOrdemProducao> execucaoDaOrdensProducao = this.execOrdemProducaoRepository.execucaoDaOrdensProducao(mensagem.obterOrdemProducao()).iterator();
        if(execucaoDaOrdensProducao.hasNext()) {
            ExecucaoOrdemProducao execOrdProd = execucaoDaOrdensProducao.next();
            
            //regista fim atividade máquina.
            execOrdProd.registarFimAtividade(maquina(), buildCalendar());
            
            if(execOrdProd.atividadeConcluida()) {
                //calculos finais..
                System.out.println("Ordem Produção " + execOrdProd.ordemProducao().identity() + " concluída na linha de produção [" + mensagem.obterLinhaProducao().identity() + "].");
                //execucaoOrdemProd.
                
                
                //******* Calcula desvios *******
                execOrdProd.updateDesvios();
                
                //Dá por concluido a ordem de producão
                OrdemProducao ordProd = this.ordemProducaoRepository.ofIdentity(mensagem.obterOrdemProducao().identity()).get();
                ordProd.updateEstadoOrdemProducao(EstadoOrdemProducao.CONCLUIDA);
                ordProd.updateDataFim(new DataFim(stringData()));
                this.ordemProducaoRepository.save(ordProd);
            }
            
            
            this.execOrdemProducaoRepository.save(execOrdProd);
            
            
        }
        
        return false;
    }
    
}
