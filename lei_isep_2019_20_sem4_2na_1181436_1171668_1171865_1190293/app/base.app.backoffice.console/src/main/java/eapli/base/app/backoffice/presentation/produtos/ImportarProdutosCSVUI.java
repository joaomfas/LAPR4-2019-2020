package eapli.base.app.backoffice.presentation.produtos;

import eapli.base.gestaoprodutos.application.ImportarProdutosCSVController;
import eapli.framework.application.Controller;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;


public class ImportarProdutosCSVUI extends AbstractUI{

    private final ImportarProdutosCSVController theController = new ImportarProdutosCSVController();
    
    protected Controller controller() {
        return this.theController;
    }
    
    @Override
    protected boolean doShow() {
        final String path = Console.readLine("Caminho do ficheiro CSV:");
        
        try {
            int contador = this.theController.importarProdutosCSV(System.getProperty("user.home")+path);
            System.out.println("Foram importados "+contador+" produtos!");
        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("Erro ao importar produtos!");
        }
        return false;
    }

    @Override
    public String headline() {
        return "Importar Produtos CSV";
    }

}
