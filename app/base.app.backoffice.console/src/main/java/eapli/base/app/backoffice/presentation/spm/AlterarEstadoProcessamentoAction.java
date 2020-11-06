
package eapli.base.app.backoffice.presentation.spm;

import eapli.framework.actions.Action;

/**
 *
 * @author mdias
 */
public class AlterarEstadoProcessamentoAction implements Action{
    @Override
    public boolean execute() {
        return new AlterarEstadoProcessamentoUI().show();
    }
}
