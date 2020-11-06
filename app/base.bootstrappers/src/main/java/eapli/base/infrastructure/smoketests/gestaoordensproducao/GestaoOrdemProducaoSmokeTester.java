package eapli.base.infrastructure.smoketests.gestaoordensproducao;

import eapli.base.gestaoordensproducao.application.ImportarOrdensProducaoCSVController;
import eapli.framework.actions.Action;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GestaoOrdemProducaoSmokeTester implements Action{

    private static final Logger LOGGER = LogManager.getLogger(GestaoOrdemProducaoSmokeTester.class);
    final OrdemProducaoCRUDTester crudTester = new OrdemProducaoCRUDTester();
    
    @Override
    public boolean execute() {
        testOrdemProducaoCRUD();
        importarOrdensProducaoCSV();
        return true;
    }

    private void testOrdemProducaoCRUD() {
        crudTester.testOrdemProducaoCRUD();
    }
    
    private void importarOrdensProducaoCSV() {
        final ImportarOrdensProducaoCSVController controller = new ImportarOrdensProducaoCSVController();
        
        int cont = controller.importarOrdensProducaoCSV(System.getProperty("user.dir")+"/ficheirosTeste/ordensProducao.csv");
        
        LOGGER.info("--- IMPORTAR ORDENS PRODUCAO CSV ---");
        LOGGER.info("Importadas " + cont + " ordens de producao.");
    }
}
