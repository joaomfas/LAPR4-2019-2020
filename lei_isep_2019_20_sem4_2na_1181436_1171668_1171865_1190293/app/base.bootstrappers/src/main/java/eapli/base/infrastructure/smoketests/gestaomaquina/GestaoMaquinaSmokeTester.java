/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.infrastructure.smoketests.gestaomaquina;

import eapli.framework.actions.Action;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author 35193
 */
public class GestaoMaquinaSmokeTester implements Action {
    
    private static final Logger LOGGER = LogManager.getLogger(GestaoMaquinaSmokeTester.class);
    final MaquinaCRUDSmokeTester crudTester = new MaquinaCRUDSmokeTester();

    @Override
    public boolean execute() {
        testMaquinaCRUD();
        return true;
    }

    private void testMaquinaCRUD() {
        crudTester.testMaquinaCRUD();
    }
    
}
