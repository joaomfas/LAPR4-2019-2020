package eapli.base.app.backoffice.presentation.materiasprimas;

import eapli.framework.actions.Action;

public class RegistarMateriaPrimaAction implements Action{
    @Override
    public boolean execute() {
        return new RegistarMateriaPrimaUI().show();
    }
}
