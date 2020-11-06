package eapli.base.app.scm.console.gestaomensagens;

import eapli.framework.actions.Action;


public class SCMServerAction implements Action {

    @Override
    public boolean execute() {
        return new SCMServerUI().doShow();
    }

}
