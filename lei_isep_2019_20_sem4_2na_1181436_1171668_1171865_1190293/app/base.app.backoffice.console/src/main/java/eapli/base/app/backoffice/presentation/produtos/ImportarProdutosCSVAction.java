package eapli.base.app.backoffice.presentation.produtos;

import eapli.framework.actions.Action;


public class ImportarProdutosCSVAction implements Action{

    @Override
    public boolean execute() {
        return new ImportarProdutosCSVUI().doShow();
    }

}
