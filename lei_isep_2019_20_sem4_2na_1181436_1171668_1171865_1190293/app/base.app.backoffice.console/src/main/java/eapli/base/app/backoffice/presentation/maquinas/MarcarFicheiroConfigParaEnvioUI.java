package eapli.base.app.backoffice.presentation.maquinas;

import eapli.base.gestaomaquinas.application.MarcarFicheiroParaEnvioController;
import eapli.base.gestaomaquinas.domain.Maquina;
import eapli.base.gestaomaquinas.domain.MaquinaConfigFile;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import java.util.ArrayList;


public class MarcarFicheiroConfigParaEnvioUI extends AbstractUI{

     private final MarcarFicheiroParaEnvioController theController = new MarcarFicheiroParaEnvioController();

    protected Controller controller() {
        return this.theController;
    }
    
    @Override
    protected boolean doShow() {
        final Iterable<Maquina> mqs = theController.todasMaquinas();
        
        if(((ArrayList<Maquina>)mqs).isEmpty()) {
            System.out.println("Não existem Maquinas registadas.");
            return false;
        }
        System.out.printf("%-10s", "Código Interno");
        System.out.printf("");
        final SelectWidget<Maquina> selector = new SelectWidget<>("Maquina :", mqs, new MaquinasPrinter());
        selector.show();
        final Maquina maquina = selector.selectedElement();
        
        if(maquina == null){
            return false;
        }
        
        if(maquina.ficheirosConfig().isEmpty()){
            System.out.println("Máquina sem ficheiros de configuração associados!");
        }
        
        System.out.printf("%-20s%-20s", "Descrição", "Marcado para envio");
        final SelectWidget<MaquinaConfigFile> selector1 = new SelectWidget<>("Ficheiros :", maquina.ficheirosConfig(), new FicheiroConfigPrinter());
        selector1.show();
        final MaquinaConfigFile file = selector1.selectedElement();
        
        if(file == null){
            return false;
        }
        
        theController.marcarFichParaEnvio(maquina, file);
        
        return false;
    }

    @Override
    public String headline() {
        return "Marcar ficheiro de configuração para envio";
    }

}
