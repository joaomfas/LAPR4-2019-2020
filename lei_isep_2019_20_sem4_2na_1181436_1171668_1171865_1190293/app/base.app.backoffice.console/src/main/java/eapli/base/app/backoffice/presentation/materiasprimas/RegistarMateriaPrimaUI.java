
package eapli.base.app.backoffice.presentation.materiasprimas;

import eapli.base.gestaomateriasprimas.application.RegistarMateriaPrimaController;
import eapli.base.gestaomateriasprimas.domain.CategoriaMateriaPrima;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;
import java.util.ArrayList;

public class RegistarMateriaPrimaUI extends AbstractUI {
    private final RegistarMateriaPrimaController theController = new RegistarMateriaPrimaController();

    protected Controller controller() {
        return this.theController;
    }
    
    @Override
    protected boolean doShow() {
        final int EXIT_OPTION = 0;
        final Iterable<CategoriaMateriaPrima> categoriasMP = this.theController.categoriasMateriasPrimas();
        
        if(((ArrayList<CategoriaMateriaPrima>)categoriasMP).isEmpty()) {
            System.out.println("Não existem categorias de matéria prima registadas.");
            return false;
        }
        
        final SelectWidget<CategoriaMateriaPrima> selector = new SelectWidget<>("Categorias matéria prima:", categoriasMP,
                new CategoriaMateriaPrimaPrinter());
        selector.show();
        
        if(selector.selectedOption() == EXIT_OPTION){
            return false;
        }
        
        final CategoriaMateriaPrima aCategoriaMP = selector.selectedElement();
        final String codigoInterno = Console.readLine("Código Interno:");
        final String descricao = Console.readLine("Descrição:");
        final String fichaTecnicaPath = System.getProperty("user.home")+Console.readLine("Caminho da ficha Técnica:");
        final String unidadeMedida = Console.readLine("Unidade de Medida:");

        try {
            this.theController.registarMateriaPrima(codigoInterno, aCategoriaMP, descricao, 
                    fichaTecnicaPath, unidadeMedida);
        } catch (@SuppressWarnings("unused") final Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public String headline() {
        return "Regitar Matéria Prima";
    }
}
