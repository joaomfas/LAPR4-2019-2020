package eapli.base.app.spm.console.gestaoprocessamento;

import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import eapli.base.gestaomaquinas.application.EspecificarMaquinaController;
import eapli.base.spm.application.AgendarProcessamentoController;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AgendarProcessamentoUI extends AbstractUI {

    private final EspecificarMaquinaController maquinaController = new EspecificarMaquinaController();

    private final AgendarProcessamentoController controller = new AgendarProcessamentoController();

    final int EXIT_OPTION = 0;

    final int AGENDAR_POR_LINHA_LINHAS = 1;

    final int AGENDAR_TODAS_LINHAS = 2;

    @Override
    protected boolean doShow() {

        List<String> options = new ArrayList<>();
        options.add("Agendar por Linha de Producão");
        options.add("Agendar tudo");

        final SelectWidget<String> selectorOption = new SelectWidget<>("Filtros:", options,
                new OptionPrinter());
        selectorOption.show();

        int opcao = selectorOption.selectedOption();

        switch (opcao) {
            case AGENDAR_POR_LINHA_LINHAS:
                return agendarPorLinha();
            case AGENDAR_TODAS_LINHAS:
                return agendarTudo();
            case EXIT_OPTION:
                return false;
            default:
                break;
        }
        return false;

    }

    public boolean agendarPorLinha() {
        String dateA = "";

        Calendar dataInicio = Calendar.getInstance();
        do {
            do {
                dateA = Console.readLine("Hora inicio (hh:mm) e.g 20:40 :");
            } while (!dateA.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]"));
            dataInicio.set(Calendar.HOUR_OF_DAY, Integer.parseInt(dateA.split(":")[0]));
            dataInicio.set(Calendar.MINUTE, Integer.parseInt(dateA.split(":")[1]));
            if (Calendar.getInstance().after(dataInicio)) {
                System.out.println("Especifique uma hora futura.");
            }
        } while (Calendar.getInstance().after(dataInicio));

        String dateB = "";

        Calendar dataFim = Calendar.getInstance();
        do {
            do {
                dateB = Console.readLine("Hora Fim (hh:mm) e.g 21:00 :");
            } while (!dateB.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]"));
            dataFim.set(Calendar.HOUR_OF_DAY, Integer.parseInt(dateB.split(":")[0]));
            dataFim.set(Calendar.MINUTE, Integer.parseInt(dateB.split(":")[1]));
            if (dataInicio.after(dataFim)) {
                System.out.println("Especifique uma hora posterior à hora de início!.");
            }
        } while (dataInicio.after(dataFim));

        Iterable<LinhaProducao> linhasProducao = this.maquinaController.linhasProducao();

        LinhaProducao linhaProducao;

        List<LinhaProducao> linhasSelecionadas = new ArrayList();

        do {
            linhaProducao = null;

            SelectWidget<LinhaProducao> selector = new SelectWidget<>("Linhas de produção a serem Processadas [ 0 para terminar seleção ] :", linhasProducao, new LinhasProducaoPrinter());

            if (((ArrayList<LinhaProducao>) linhasProducao).isEmpty()) {
                System.out.println("Não existem linhas de producao.");
                return false;
            }

            selector.show();

            linhaProducao = selector.selectedElement();

            List<LinhaProducao> listaAtualizada = new ArrayList<LinhaProducao>();
            linhasProducao.iterator().forEachRemaining(listaAtualizada::add);
            listaAtualizada.remove(linhaProducao);
            linhasProducao = listaAtualizada;
            if (linhaProducao != null) {
                linhasSelecionadas.add(linhaProducao);
            }

        } while (linhaProducao != null);

        if (linhasSelecionadas.size() != 0) {
            linhasProducao = linhasSelecionadas;
        }

        controller.iniciarAgendamento(linhasProducao, dataInicio, dataFim);

        return false;
    }

    private boolean agendarTudo() {
        String dateA = "";

        Calendar dataInicio = Calendar.getInstance();
        do {
            do {
                dateA = Console.readLine("Hora inicio (hh:mm) e.g 20:40 :");
            } while (!dateA.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]"));
            dataInicio.set(Calendar.HOUR_OF_DAY, Integer.parseInt(dateA.split(":")[0]));
            dataInicio.set(Calendar.MINUTE, Integer.parseInt(dateA.split(":")[1]));
            if (Calendar.getInstance().after(dataInicio)) {
                System.out.println("Especifique uma hora futura.");
            }
        } while (Calendar.getInstance().after(dataInicio));

        String dateB = "";

        Calendar dataFim = Calendar.getInstance();
        do {
            do {
                dateB = Console.readLine("Hora Fim (hh:mm) e.g 21:00 :");
            } while (!dateB.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]"));
            dataFim.set(Calendar.HOUR_OF_DAY, Integer.parseInt(dateB.split(":")[0]));
            dataFim.set(Calendar.MINUTE, Integer.parseInt(dateB.split(":")[1]));
            if (dataInicio.after(dataFim)) {
                System.out.println("Especifique uma hora posterior à hora de início!.");
            }
        } while (dataInicio.after(dataFim));

        Iterable<LinhaProducao> linhasProducao = this.maquinaController.linhasProducao();
        controller.iniciarAgendamento(linhasProducao, dataInicio, dataFim);
        
        return false;
    }

    @Override
    public String headline() {
        return "SPM : Agendar Processamento de Mensagens";
    }

}
