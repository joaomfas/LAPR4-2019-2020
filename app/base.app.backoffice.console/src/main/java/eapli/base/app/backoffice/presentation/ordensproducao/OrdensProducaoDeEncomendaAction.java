package eapli.base.app.backoffice.presentation.ordensproducao;

import eapli.framework.actions.Action;

/**
 *
 * @author mdias
 */
public class OrdensProducaoDeEncomendaAction implements Action{
    @Override
    public boolean execute() {
        return new OrdensProducaoDeEncomendaUI().show();
    }
}
