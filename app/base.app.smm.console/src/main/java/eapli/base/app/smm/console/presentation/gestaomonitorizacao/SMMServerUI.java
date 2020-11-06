package eapli.base.app.smm.console.presentation.gestaomonitorizacao;

import eapli.base.smm.application.MonitorizacaoController;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

public class SMMServerUI extends AbstractUI {


    
    private final MonitorizacaoController controller = new MonitorizacaoController();
    
    @Override
    protected boolean doShow() {
        
        String broadcastIp = Console.readLine("Endereço de broadcast (192.168.1.255):");
        if(broadcastIp.isEmpty())
            broadcastIp = "192.168.1.255";
        //controller.iniciarMonitorizacao(broadcastIp, porta);
        controller.iniciarMonitorizacao();
        controller.encontrarMaquinas(broadcastIp);
        
        return false;
    }

    @Override
    public String headline() {
        return "SMMServer";
    }
    
}