package eapli.base.app.backoffice.presentation.produtos;

import eapli.framework.actions.Action;


public class ApresentarProdutosSemFichaAction implements Action {

    @Override
    public boolean execute() {
        return new ApresentarProdutosSemFichaUI().show();
    }

}
