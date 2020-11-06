package eapli.base.app.spm.console.gestaoprocessamento;

import eapli.framework.actions.Action;

public class IniciarProcessamentoRecorrenteAction implements Action {

    @Override
    public boolean execute() {
        return new IniciarProcessamentoRecorrenteUI().doShow();
    }
    
}
