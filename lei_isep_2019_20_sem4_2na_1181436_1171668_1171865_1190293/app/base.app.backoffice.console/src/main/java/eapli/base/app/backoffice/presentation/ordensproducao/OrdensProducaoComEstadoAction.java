package eapli.base.app.backoffice.presentation.ordensproducao;

import eapli.framework.actions.Action;

public class OrdensProducaoComEstadoAction implements Action {

    @Override
    public boolean execute() {
        return new OrdensProducaoComEstadoUI().show();
    }

}
