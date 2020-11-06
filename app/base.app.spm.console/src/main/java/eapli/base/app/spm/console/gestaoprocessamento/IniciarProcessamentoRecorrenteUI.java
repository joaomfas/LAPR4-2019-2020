package eapli.base.app.spm.console.gestaoprocessamento;

import eapli.base.spm.application.IniciarProcessamentoRecorrenteController;
import eapli.framework.presentation.console.AbstractUI;

public class IniciarProcessamentoRecorrenteUI extends AbstractUI {

    IniciarProcessamentoRecorrenteController controller = new IniciarProcessamentoRecorrenteController ();
    
    @Override
    protected boolean doShow() {
        
        controller.iniciarProcessamentoRecorrente();
        System.out.println("Processamento de Mensagens Iniciado");
        return false;
    }

    @Override
    public String headline() {
        return "Iniciar processamento de mensagens recorrente";
    }
    
}
