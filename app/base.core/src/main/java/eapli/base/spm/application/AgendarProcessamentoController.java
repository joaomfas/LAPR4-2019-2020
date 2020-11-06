package eapli.base.spm.application;

import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import eapli.framework.application.Controller;
import java.util.Calendar;

public class AgendarProcessamentoController implements Controller {
    
    public void iniciarAgendamento(Iterable<LinhaProducao> linhasProducao, Calendar dataInicio, Calendar dataFim) {
        
        System.out.println("Agendamento inicado");
        
        new Thread(new AgendamentoTimerRunnable(linhasProducao, dataInicio, dataFim)).start();
        
    }
}