package eapli.base.app.backoffice.presentation.materiasprimas;

import eapli.framework.actions.Action;

public class RegistarCategoriaMateriaPrimaAction implements Action{

    @Override
    public boolean execute() {
        return new RegistarCategoriaMateriaPrimaUI().show();
    }
}
