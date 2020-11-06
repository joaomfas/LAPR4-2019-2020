package eapli.base.spm.application;

import eapli.base.gestaolinhasproducao.domain.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProcessamentoRecorrenteRunnable implements Runnable {

    private final AtualizarProcessamentoService atualizarSvc = new AtualizarProcessamentoService();
    
    private final int MENSAGENS_POR_INTERVALO = 100;

    private final int INTERVALO = 10000;

    private static Thread thrLinhaEnriquecimento = null;

    private static Map<LinhaProducao, ProcessamentoRunnable> processamentoLinhaProducao = new HashMap();

    ProcessamentoRecorrenteRunnable(Iterable<LinhaProducao> linhas) {

        new EnriquecimentoService().iniciaEnriquecimento();

        for (LinhaProducao linha : linhas) {
            if (!processamentoLinhaProducao.containsKey(linha)) {
                processamentoLinhaProducao.put(linha, new ProcessamentoRunnable(linha, MENSAGENS_POR_INTERVALO));
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                LinkedList<Thread> thr = new LinkedList();

                for (LinhaProducao l : processamentoLinhaProducao.keySet()) {
                    if (l.estadoProcessamentoRecorrente() == EstadoProcessamentoRecorrente.ATIVO) {
                        Thread t = new Thread(processamentoLinhaProducao.get(l));
                        thr.add(t);
                        t.start();
                        //ultima vez que correu
                        atualizarSvc.updateLinhaProducao(l);
                    }
                }

                for (Thread t : thr) {
                    t.join();
                }
                Thread.sleep(INTERVALO);
            } catch (InterruptedException ex) {
                Logger.getLogger(ProcessamentoRecorrenteRunnable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


}
