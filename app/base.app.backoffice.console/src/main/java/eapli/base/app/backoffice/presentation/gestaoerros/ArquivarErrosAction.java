package eapli.base.app.backoffice.presentation.gestaoerros;

import eapli.framework.actions.Action;

public class ArquivarErrosAction implements Action {

    @Override
    public boolean execute() {
        return new ArquivarErrosUI().doShow();
    }
    
}
