package eapli.base.app.scm.console.gestaomensagens;

import eapli.framework.actions.Action;

public class ImportarMensagensAction implements Action {

    @Override
    public boolean execute() {
        return new ImportarMensagensUI().doShow();
    }

}
