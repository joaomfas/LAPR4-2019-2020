package eapli.base.app.backoffice.presentation.maquinas;

import eapli.base.gestaomaquinas.application.ImportarFichConfigMaquinaController;
import eapli.base.gestaomaquinas.application.ListarMaquinasService;
import eapli.base.gestaomaquinas.domain.Maquina;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;
import java.util.ArrayList;


public class AdicionarFicheiroConfigMaquinaUI extends AbstractUI {

    private final ListarMaquinasService lms = new ListarMaquinasService();
    
    private final ImportarFichConfigMaquinaController controller = new ImportarFichConfigMaquinaController();
    
    @Override
    protected boolean doShow() {
        
        final Iterable<Maquina> maquinas = this.lms.todasMaquinas();
        if(((ArrayList<Maquina>)maquinas).isEmpty()) {
            System.out.println("Não existem Maquinas registadas.");
            return false;
        }
        final SelectWidget<Maquina> selector = new SelectWidget<>("Maquina :", maquinas, new MaquinasPrinter());
        selector.show();
        final Maquina maquina = selector.selectedElement();
        final String path = System.getProperty("user.home") + Console.readLine("Caminho do ficheiro de configuração:");
        final String descricao = Console.readLine("Breve descricao do ficheiro de configuração :");
        try {
            controller.importarFichConfig(maquina, path, descricao);
        } catch (Exception e) {
            System.out.println("Erro ao importar ficheiro");
        }
        
        return false;
    }

    @Override
    public String headline() {
        return "Adicionar Ficheiro de configuração a Maquina";
    }
    
}
