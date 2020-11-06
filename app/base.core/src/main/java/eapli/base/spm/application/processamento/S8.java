package eapli.base.spm.application.processamento;

import eapli.base.gestaomensagens.dominio.Mensagem;
import eapli.base.gestaoordensproducao.domain.ExecucaoOrdemProducao;
import eapli.base.spm.application.processamento.validacao.exceptions.SPMException;
import java.util.Iterator;

//S8 -> Paragem
//S8 -> Máquina;TipoMsg;DataHora
public class S8 extends ProcessaMensagem {
    
    public S8(Mensagem mensagem) throws SPMException {
        super(mensagem);
    }
    
    
    @Override
    public boolean processa() throws SPMException {
        if(mensagem.obterOrdemProducao()==null) return false;
        
        Iterator<ExecucaoOrdemProducao> execucaoDaOrdensProducao = this.execOrdemProducaoRepository.execucaoDaOrdensProducao(mensagem.obterOrdemProducao()).iterator();
        if(execucaoDaOrdensProducao.hasNext()) {
            ExecucaoOrdemProducao execOrdProd = execucaoDaOrdensProducao.next();
            execOrdProd.registarParagemAtividade(maquina(), buildCalendar());
            this.execOrdemProducaoRepository.save(execOrdProd);
        }
        
        return false;
    }
    
    
}
