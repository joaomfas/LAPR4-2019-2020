/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.app.backoffice.presentation.linhasproducao;

import eapli.framework.actions.Action;

/**
 *
 * @author 35193
 */
public class RegistarLinhaProducaoAction implements Action {
    @Override
    public boolean execute() {
        return new RegistarLinhaProducaoUI().doShow();
    }
}
