package eapli.base.app.backoffice.presentation.gestaoerros;

import eapli.base.app.backoffice.presentation.produtos.OptionPrinter;
import eapli.base.gestaoordensproducao.application.ArquivarErrosPendentesController;
import eapli.base.gestaoordensproducao.application.ConsultarErrosPendentesController;
import eapli.base.gestaoordensproducao.domain.ErroProcessamento;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import java.util.ArrayList;
import java.util.List;

public class ArquivarErrosUI extends AbstractUI {
    
    private final ConsultarErrosPendentesController consultarController = new ConsultarErrosPendentesController();
    
    private final ArquivarErrosPendentesController arquivarController = new ArquivarErrosPendentesController();
    
    final int EXIT_OPTION = 0;
    final int ARQUIVAR_ERROS = 1;
    final int ARQUIVAR_TODOS_ERROS = 2;
    
    @Override
    protected boolean doShow() {
        
        List<String> options = new ArrayList<>();
        options.add("Arquivar erros pendentes");
        options.add("Arquivar todos os erros pendentes existentes.");
        
        
        final SelectWidget<String> selectorOption = new SelectWidget<>("Filtros:", options,
                new OptionPrinter());
        selectorOption.show();
        
        int opcao = selectorOption.selectedOption();

        switch (opcao) {
            case ARQUIVAR_ERROS:
                arquivarErrosPendentes();
                return false;
            case ARQUIVAR_TODOS_ERROS:
                arquivarTodos();
                return false;
            case EXIT_OPTION:
                return false;
            default:
                break;
        }
        return false;

    }

    @Override
    public String headline() {
        return "Arquivar Erros";
    }

    private void arquivarErrosPendentes() {
        
        Iterable<ErroProcessamento> erros = consultarController.consultarErrosPendentes();
        
        final SelectWidget<ErroProcessamento> selectorOption = new SelectWidget<>(":", erros,
                new ErroProcessamentoPrinter());
        selectorOption.show();
        
        int opcao = selectorOption.selectedOption();

        if (opcao==EXIT_OPTION) {
            return;
        } else {
            List<ErroProcessamento> errosAArquivar = new ArrayList();
            errosAArquivar.add(selectorOption.selectedElement());
            
            arquivarController.arquivarErrosPendentes(errosAArquivar);
        }

    }


    private void arquivarTodos() {
        
        List<ErroProcessamento> errosAArquivar = new ArrayList();
        Iterable<ErroProcessamento> erros = consultarController.consultarErrosPendentes();
        erros.forEach(errosAArquivar::add);
        arquivarController.arquivarErrosPendentes(errosAArquivar);
        
    }
    
}
