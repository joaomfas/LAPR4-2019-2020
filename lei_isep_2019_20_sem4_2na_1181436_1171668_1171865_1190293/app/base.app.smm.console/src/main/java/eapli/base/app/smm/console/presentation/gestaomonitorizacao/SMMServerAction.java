package eapli.base.app.smm.console.presentation.gestaomonitorizacao;

import eapli.framework.actions.Action;

public class SMMServerAction implements Action {
    
    @Override
    public boolean execute() {
        return new SMMServerUI().doShow();
    }
    
}
