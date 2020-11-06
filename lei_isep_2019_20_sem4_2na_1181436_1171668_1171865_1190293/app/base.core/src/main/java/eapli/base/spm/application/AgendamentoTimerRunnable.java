package eapli.base.spm.application;

import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;

public class AgendamentoTimerRunnable implements Runnable {

    private final AtualizarProcessamentoService atualizarSvc = new AtualizarProcessamentoService();
    
    private final Calendar dataInicio;

    private final Calendar dataFim;

    private final Iterable<LinhaProducao> linhasProducao;

    private final List<Thread> threadlinhas;

    public AgendamentoTimerRunnable(Iterable<LinhaProducao> linhasProducao, Calendar dataInicio, Calendar dataFim) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.linhasProducao = linhasProducao;
        this.threadlinhas = new ArrayList();
        new EnriquecimentoService().iniciaEnriquecimento();
    }

    @Override
    public void run() {

        //******************* DEMO só espera 2 segundos. ***************************//      
//        while (dataInicio.after(Calendar.getInstance())) {
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        
        //Processa 20 mensagens / segundo até terminar o tempo de agendamento
        while (dataFim.after(Calendar.getInstance())) {
            try {

                List<Thread> thr = new ArrayList();
                for (LinhaProducao linhaProd : linhasProducao) {  
                    Thread t = new Thread(new ProcessamentoRunnable(linhaProd, 50));
                    thr.add(t);
                    t.start();
                    atualizarSvc.updateLinhaProducao(linhaProd);
                }

                while(thr.size()>0) {
                    Thread t = thr.remove(0);
                    t.join();
                }
                        
                //Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }

        System.out.println(" Fim do processamento manual.. ");
    }

}
