package eapli.base.app.backoffice.presentation.gestaochaofabrica;

import eapli.framework.actions.Action;


public class ExportarChaoFabricaXMLAction implements Action{

    @Override
    public boolean execute() {
        return new ExportarChaoFabricaXMLUI().doShow();
    }

}
