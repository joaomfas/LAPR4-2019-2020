package eapli.base.app.backoffice.presentation.produtos;

import eapli.framework.actions.Action;


public class RegistarProdutoAction implements Action {

    @Override
    public boolean execute() {
        return new RegistarProdutoUI().show();
    }

}
