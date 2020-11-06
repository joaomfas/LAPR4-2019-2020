package eapli.base.app.backoffice.presentation.gestaoerros;

import eapli.base.app.backoffice.presentation.produtos.OptionPrinter;
import eapli.base.gestaolinhasproducao.application.ListarLinhasProducaoService;
import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import eapli.base.gestaoordensproducao.application.ConsultarErrosPendentesController;
import eapli.base.gestaoordensproducao.domain.ErroProcessamento;
import eapli.base.spm.application.processamento.validacao.exceptions.EnumErros;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import java.util.ArrayList;
import java.util.List;

public class ConsultarErrosPendentesUI extends AbstractUI {
    
    final int EXIT_OPTION = 0;
    final int ERROS_PENDENTES = 1;
    final int ERROS_PENDENTES_LINHAPRODUCAO = 2;

    ConsultarErrosPendentesController theController = new ConsultarErrosPendentesController();
    ListarLinhasProducaoService linhaProducaoService = new ListarLinhasProducaoService();
    
    @Override
    protected boolean doShow() {
        List<String> options = new ArrayList<>();
        options.add("Erros pendentes");
        options.add("Erros pendentes por LinhaProducao");
        
        final SelectWidget<String> selectorOption = new SelectWidget<>("Filtros:", options,
                new OptionPrinter());
        selectorOption.show();
        
        int opcao = selectorOption.selectedOption();

        switch (opcao) {
            case ERROS_PENDENTES:
                errosPendentes();
                return false;
            case ERROS_PENDENTES_LINHAPRODUCAO:
                errosPendentesLinhaProducao();
                return false;
            case EXIT_OPTION:
                return false;
            default:
                break;
        }
        return false;
    }

    

    private void errosPendentes() {
        
        Iterable<ErroProcessamento> erros = theController.consultarErrosPendentes();
        if (((ArrayList<ErroProcessamento>)erros).isEmpty()) {
            System.out.println("Não existem erros que cumpram os critérios");
            return;
        }
        System.out.printf("%-10s%-20s%-50s%-20s", "ID",
                "Estado", "Tipo erro", "Data erro");
        for (ErroProcessamento er : erros) {
            new ErroProcessamentoPrinter().visit(er);
        }
        
    }

    private void errosPendentesLinhaProducao() {
        
        Iterable<LinhaProducao> linhasProducao = linhaProducaoService.allLinhasProducao();
        final SelectWidget<LinhaProducao> selector = new SelectWidget<>("Tipos de erros:", linhasProducao,
                new LinhasProducaoPrinter());
        selector.show();

        if (selector.selectedOption() != EXIT_OPTION) {
            LinhaProducao linhaProducao = selector.selectedElement();
            Iterable<ErroProcessamento> erros = theController.consultarErrosPendentes(linhaProducao);
            if (((ArrayList<ErroProcessamento>)erros).isEmpty()) {
                System.out.println("Não existem erros que cumpram os critérios");
                return;
            }
            System.out.printf("%-10s%-20s%-50s%-20s", "ID",
                    "Estado", "Tipo erro", "Data erro");
            for (ErroProcessamento er : erros) {
                new ErroProcessamentoPrinter().visit(er);
            }
        }
        
    }
    
    
    @Override
    public String headline() {
        return "Consultar erros pendentes";
    }
    
}
