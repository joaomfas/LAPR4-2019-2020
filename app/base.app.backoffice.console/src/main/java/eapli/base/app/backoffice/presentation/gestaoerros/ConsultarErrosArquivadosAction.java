package eapli.base.app.backoffice.presentation.gestaoerros;

import eapli.framework.actions.Action;


public class ConsultarErrosArquivadosAction implements Action {

    @Override
    public boolean execute() {
        return new ConsultarErrosArquivadosUI().doShow();
    }

}
