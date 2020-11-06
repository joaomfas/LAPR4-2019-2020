package eapli.base.app.backoffice.presentation.maquinas;

import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import eapli.base.gestaomaquinas.application.EspecificarMaquinaController;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;
import java.util.ArrayList;

public class EspecificarMaquinaUI extends AbstractUI {
    
    private final EspecificarMaquinaController theController = new EspecificarMaquinaController();
    
    
    protected Controller controller() {
        return this.theController;
    }
    
    @Override
    protected boolean doShow() {
        final Iterable<LinhaProducao> linhasProducao = this.theController.linhasProducao();
        
        if(((ArrayList<LinhaProducao>)linhasProducao).isEmpty()) {
            //throw new IllegalArgumentException("Não existem linhas registadas.");
            System.out.println("Não existem linhas registadas.");
            return false;
        }
        
        final SelectWidget<LinhaProducao> selector = new SelectWidget<>("Linhas Produção:", linhasProducao, new LinhasProducaoPrinter());
        selector.show();
        final LinhaProducao linhaProducao = selector.selectedElement();

        final Long numSeq = Long.parseLong(Console.readLine("Número de Sequência:"));
        
        final String numIdUnico = Console.readLine("Número de Identificação Único:");
        
        final String codInterno = Console.readLine("Código Interno:");
        
        final String descricao = Console.readLine("Descrição:");
        
        final String numserie = Console.readLine("Número de Série:");
        
        final String datainstalacao = Console.readLine("Data de Instalação(DD/MM/AAAA):");
        
        final String marca = Console.readLine("Marca:");
        
        final String modelo = Console.readLine("Modelo:");
        
        

        try {
            this.theController.registerMaquina(linhaProducao.identity(), numSeq, numIdUnico, codInterno, 
                    descricao, numserie, datainstalacao, marca, modelo);
        } catch (@SuppressWarnings("unused") final Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public String headline() {
        return "Especificar Maquina";
    }
}
