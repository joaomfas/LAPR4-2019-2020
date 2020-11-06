package eapli.base.app.spm.console.gestaoprocessamento;

import eapli.framework.actions.Action;

public class AgendarProcessamentoAction implements Action {

    @Override
    public boolean execute() {
        return new AgendarProcessamentoUI().doShow();
    }
    
}
