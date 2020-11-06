package eapli.base.app.backoffice.presentation.spm;

import eapli.base.app.backoffice.presentation.maquinas.LinhasProducaoPrinter;
import eapli.base.gestaolinhasproducao.domain.EstadoProcessamentoRecorrente;
import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import eapli.base.spm.application.AgendarProcessamentoController;
import eapli.base.spm.application.AlterarEstadoProcessamentoController;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author mdias
 */
public class AlterarEstadoProcessamentoUI extends AbstractUI {

    final int EXIT_OPTION = 0;
    final int ATIVAR_OPTION = 1;
    final int DESATIVAR_OPTION = 2;
    final int AGENDAR_POR_LINHA = 1;

    private final AlterarEstadoProcessamentoController theController = new AlterarEstadoProcessamentoController();
    private final AgendarProcessamentoController agendarController = new AgendarProcessamentoController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {

        Iterable<LinhaProducao> linhas = theController.linhasProducao();
        if (((ArrayList<LinhaProducao>) linhas).isEmpty()) {
            System.out.println("Não existem Linhas de Produção");
            return false;
        }
        System.out.println("Selecione a Linha de Produção:");
        final SelectWidget<LinhaProducao> selectorLinha = new SelectWidget<>("", linhas,
                new LinhasProducaoPrinter());
        selectorLinha.show();
        if (selectorLinha.selectedOption() == EXIT_OPTION) {
            return false;
        }

        final LinhaProducao linha = selectorLinha.selectedElement();
        new EstadoProcessamentoDeLinhaPrinter().visit(linha);

        EstadoProcessamentoRecorrente[] estados = theController.estadosProcessamentoRecorrente();
        Iterable<EstadoProcessamentoRecorrente> listaEstados = Arrays.asList(estados);

        System.out.println("");
        System.out.println("Selecione o estado para o qual pretende alterar:");
        final SelectWidget<EstadoProcessamentoRecorrente> selectorEstadoProcessamento = new SelectWidget<>("", listaEstados,
                new EstadosProcessamentoRecorrentePrinter());
        selectorEstadoProcessamento.show();

        int opcao = -1;
        opcao = selectorEstadoProcessamento.selectedOption();

        switch (opcao) {
            case ATIVAR_OPTION:
                if (!opcaoProcessamentoAutomatico(linha.identity())) {
                    return false;
                }
                break;
            case DESATIVAR_OPTION:
                if (opcaoProcessamentoManual(linha.identity())) {
                    List<LinhaProducao> linha1 = new ArrayList<>();
                    linha1.add(linha);
                    List<String> options = new ArrayList<>();
                    options.add("Agendar processamento manual");

                    final SelectWidget<String> selectorOption = new SelectWidget<>("Filtros:", options,
                            new OptionPrinter());
                    selectorOption.show();

                    int opcao1 = selectorOption.selectedOption();

                    switch (opcao1) {
                        case AGENDAR_POR_LINHA:
                            return permitirAgendarProcessamento(linha1);
                        case EXIT_OPTION:
                            return false;
                        default:
                            break;
                    }
                    return false;
                }
                break;
            case EXIT_OPTION:
                return false;
            default:
                break;
        }

        return false;
    }

    private boolean opcaoProcessamentoManual(final String idLinhaProducao) {
        return theController.alterarEstadoProcessamento(idLinhaProducao, EstadoProcessamentoRecorrente.INATIVO);
    }

    private boolean opcaoProcessamentoAutomatico(final String idLinhaProducao) {
        return theController.alterarEstadoProcessamento(idLinhaProducao, EstadoProcessamentoRecorrente.ATIVO);
    }
    
    public boolean permitirAgendarProcessamento (Iterable<LinhaProducao> linhasProducao){
        
        //Definir data inicio do processamento manual
        String dateA = "";
        Calendar dataInicio = Calendar.getInstance();
        do {
            do {
                dateA = Console.readLine("Hora inicio (hh:mm) e.g 20:40 :");
            } while (!dateA.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]"));
            dataInicio.set(Calendar.HOUR_OF_DAY, Integer.parseInt(dateA.split(":")[0]));
            dataInicio.set(Calendar.MINUTE, Integer.parseInt(dateA.split(":")[1]));
            if (Calendar.getInstance().after(dataInicio)) {
                System.out.println("Especifique uma hora futura!");
            }
        } while (Calendar.getInstance().after(dataInicio));

        //Definir data fim do processamento manual
        String dateB = "";
        Calendar dataFim = Calendar.getInstance();
        do {
            do {
                dateB = Console.readLine("Hora Fim (hh:mm) e.g 21:00 :");
            } while (!dateB.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]"));
            dataFim.set(Calendar.HOUR_OF_DAY, Integer.parseInt(dateB.split(":")[0]));
            dataFim.set(Calendar.MINUTE, Integer.parseInt(dateB.split(":")[1]));
            if (dataInicio.after(dataFim)) {
                System.out.println("Especifique uma hora posterior à hora de início!");
            }
        } while (dataInicio.after(dataFim));

        agendarController.iniciarAgendamento(linhasProducao, dataInicio, dataFim);

        return false;
    }

    @Override
    public String headline() {
        return "Activar/Desativar estado de processamento de mensagens!";
    }
}
