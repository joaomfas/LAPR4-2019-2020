package eapli.base.smm.application;

import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import eapli.base.gestaolinhasproducao.domain.Sequencia;
import eapli.base.gestaomaquinas.domain.Estado;
import eapli.base.gestaomaquinas.domain.Maquina;
import eapli.base.gestaomensagens.dominio.Codes;
import eapli.base.gestaomensagens.dominio.Mensagem;
import eapli.base.gestaomensagens.dominio.Versoes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MonitorizarLinhaRunnable implements Runnable {

    private int INTERVALO_LOOP = 5000;
    
    private LinhaProducao linhaProducao;
    
    private Map<Maquina, MonitorizarMaquinaRunnable> threadMaquina;
    
    public MonitorizarLinhaRunnable(LinhaProducao linhaProducao) {
        this.linhaProducao = linhaProducao;
        this.threadMaquina = new HashMap();
        for(Sequencia seq : this.linhaProducao.listaSequencia())
        if (!threadMaquina.containsKey(seq.maquina())) {
            threadMaquina.put(seq.maquina(), new MonitorizarMaquinaRunnable(seq.maquina()));
        } 
    }

    @Override
    public void run() {
        while (true) {

            //Threads por máquina
            Set<Maquina> maquinasMonitorizadas = threadMaquina.keySet();
            //começa as threads por maquina
            List<Thread> threads = new ArrayList();
            for (Maquina maquina : maquinasMonitorizadas) {
                
                //envia mensagem Hello
                if(maquina.estado() == Estado.DISPONIVEL) {
                    Mensagem msgAEnviar = new Mensagem(Versoes.VERS_0.getVersao(), Codes.HELLO.getCode(), 0, 0, "");
                    threadMaquina.get(maquina).adicionaMensagem(msgAEnviar);
                    Thread t = new Thread(threadMaquina.get(maquina));
                    threads.add(t);
                    t.start();
                }
            }
            try {
                //espera que as threads terminem
                for (Thread t : threads) {
                    t.join();
                }
                //espera algum tempo antes de enviar pedidos novamente
                Thread.sleep(INTERVALO_LOOP);
            } catch (InterruptedException ex) {
                
            }
            
        }
    }

    
    
}
