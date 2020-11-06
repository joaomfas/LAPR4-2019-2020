package eapli.base.app.backoffice.presentation.depositos;

import eapli.framework.actions.Action;

public class RegistarDepositoAction implements Action{
    @Override
    public boolean execute() {
        return new RegistarDepositoUI().show();
    }
}
