package eapli.base.app.backoffice.presentation.maquinas;

import eapli.framework.actions.Action;

public class AdicionarFicheiroConfigMaquinaAction implements Action {

    @Override
    public boolean execute() {
        return new AdicionarFicheiroConfigMaquinaUI().doShow();
    }
    
}
