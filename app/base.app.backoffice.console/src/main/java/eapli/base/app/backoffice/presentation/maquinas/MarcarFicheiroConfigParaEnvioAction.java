package eapli.base.app.backoffice.presentation.maquinas;

import eapli.framework.actions.Action;


public class MarcarFicheiroConfigParaEnvioAction implements Action {

    @Override
    public boolean execute() {
         return new MarcarFicheiroConfigParaEnvioUI().doShow();
    }

}
