/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.app.backoffice.presentation.maquinas;

import eapli.framework.actions.Action;

/**
 *
 * @author 35193
 */
public class EspecificarMaquinaAction implements Action {

    @Override
    public boolean execute() {
        return new EspecificarMaquinaUI().doShow();
    }
    
}
