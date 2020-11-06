package eapli.base.app.backoffice.presentation.gestaoerros;

import eapli.base.app.backoffice.presentation.produtos.OptionPrinter;
import eapli.base.gestaoordensproducao.application.ConsultarErrosArquivadosPorTipoController;
import eapli.base.gestaoordensproducao.domain.ErroProcessamento;
import eapli.base.spm.application.processamento.validacao.exceptions.EnumErros;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConsultarErrosArquivadosUI extends AbstractUI {

    final int EXIT_OPTION = 0;
    final int TIPOERRO_OPTION = 1;
    final int DATAS_OPTION = 2;

    ConsultarErrosArquivadosPorTipoController theController = new ConsultarErrosArquivadosPorTipoController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        List<String> options = new ArrayList<>();
        options.add("Erros arquivados por tipo");
        options.add("Erros arquivados entre datas");

        final SelectWidget<String> selectorOption = new SelectWidget<>("Filtros:", options,
                new OptionPrinter());
        selectorOption.show();

        int opcao = selectorOption.selectedOption();

        switch (opcao) {
            case TIPOERRO_OPTION:
                errosPorTipo();
                return false;
            case DATAS_OPTION:
                errosEntreDatas();
                return false;
            case EXIT_OPTION:
                return false;
            default:
                break;
        }
        return false;
    }

    private void errosPorTipo() {
        Iterable<EnumErros> tiposErros = theController.tiposErros();
        final SelectWidget<EnumErros> selector = new SelectWidget<>("Tipos de erros:", tiposErros,
                new TipoErroPrinter());
        selector.show();

        if (selector.selectedOption() != EXIT_OPTION) {
            EnumErros tipoErro = selector.selectedElement();
            Iterable<ErroProcessamento> erros = theController.errosPorTipo(tipoErro);
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

    private void errosEntreDatas() {
        String dataIStr = Console.readLine("Data inicio (DD/MM/AAAA):");
        String dataFStr = Console.readLine("Data inicio (DD/MM/AAAA):");

        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date dataI;
        Date dataF;
        try {
            dataI = format.parse(dataIStr);
            dataF = format.parse(dataFStr);
        } catch (ParseException ex) {
            System.out.println("Data inválida!");
            return;
        }

        Iterable<ErroProcessamento> erros = theController.errosEntreDatas(dataI, dataF);
        if (((ArrayList<ErroProcessamento>)erros).isEmpty()) {
            System.out.println("Não existem erros que cumpram os critérios");
            return;
        }
        System.out.printf("%-5s%-10s%-20s%-15s", "ID",
                "Estado", "Tipo erro", "Data erro");
        for (ErroProcessamento er : erros) {
            new ErroProcessamentoPrinter().visit(er);
        }

    }

    @Override
    public String headline() {
        return "Consultar erros arquivados";
    }

}
