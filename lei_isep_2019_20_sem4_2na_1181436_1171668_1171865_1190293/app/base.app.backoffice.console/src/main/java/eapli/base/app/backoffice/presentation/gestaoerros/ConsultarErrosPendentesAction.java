package eapli.base.app.backoffice.presentation.gestaoerros;

import eapli.framework.actions.Action;


public class ConsultarErrosPendentesAction implements Action {
    @Override
    public boolean execute() {
        return new ConsultarErrosPendentesUI().doShow();
    }

}
