/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.infrastructure.smoketests.gestaolinhaproducao;
import eapli.framework.actions.Action;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 *
 * @author 35193
 */
public class GestaoLinhaProducaoSmokeTester implements Action {
    private static final Logger LOGGER = LogManager.getLogger(GestaoLinhaProducaoSmokeTester.class);
    final LinhaProducaoCRUDSmokeTester crudTester = new LinhaProducaoCRUDSmokeTester();

    @Override
    public boolean execute() {
        testLinhaProducaoCRUD();
        return true;
    }

    private void testLinhaProducaoCRUD() {
        crudTester.testLinhaProducaoCRUD();
    }

}
