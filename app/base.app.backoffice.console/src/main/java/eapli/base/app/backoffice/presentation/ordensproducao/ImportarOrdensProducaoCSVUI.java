package eapli.base.app.backoffice.presentation.ordensproducao;

import eapli.base.gestaoordensproducao.application.ImportarOrdensProducaoCSVController;
import eapli.framework.application.Controller;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

/**
 *
 * @author mdias
 */
public class ImportarOrdensProducaoCSVUI extends AbstractUI{
    private final ImportarOrdensProducaoCSVController theController = new ImportarOrdensProducaoCSVController();
    
    protected Controller controller() {
        return this.theController;
    }
    
    @Override
    protected boolean doShow() {
        final String path = Console.readLine("Caminho do ficheiro CSV:");
        
        try {
            int contador = this.theController.importarOrdensProducaoCSV(System.getProperty("user.home")+path);
            System.out.println("Foram importadas "+contador+" ordens de producao!");
        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("Erro ao importar ordens de producao!");
        }
        return false;
    }

    @Override
    public String headline() {
        return "Importar Ordens de Producao CSV";
    }
}
