package eapli.base.app.backoffice.presentation.produtos;

import eapli.framework.actions.Action;


public class RegistarFichaProducaoAction implements Action{

    @Override
    public boolean execute() {
        return new RegistarFichaProducaoUI().doShow();
    }

}
