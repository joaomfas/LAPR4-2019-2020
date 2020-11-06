package eapli.base.app.backoffice.presentation.ordensproducao;

import eapli.base.gestaoordensproducao.application.OrdensProducaoComEstadoController;
import eapli.base.gestaoordensproducao.domain.Consumo;
import eapli.base.gestaoordensproducao.domain.EstadoOrdemProducao;
import eapli.base.gestaoordensproducao.domain.Estorno;
import eapli.base.gestaoordensproducao.domain.ExecucaoOrdemProducao;
import eapli.base.gestaoordensproducao.domain.OrdemProducao;
import eapli.base.gestaoordensproducao.domain.Producao;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import java.util.ArrayList;
import java.util.Arrays;

public class OrdensProducaoComEstadoUI extends AbstractUI {

    final int EXIT_OPTION = 0;

    private final OrdensProducaoComEstadoController theController = new OrdensProducaoComEstadoController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {

        EstadoOrdemProducao[] estados = theController.estadosOrdemProducao();
        Iterable<EstadoOrdemProducao> listaEstados = Arrays.asList(estados);

        System.out.println("Selecione o estado:");
        final SelectWidget<EstadoOrdemProducao> selectorEstadoOrdem = new SelectWidget<>("", listaEstados,
                new EstadosOrdemProducaoPrinter());
        selectorEstadoOrdem.show();

        if (selectorEstadoOrdem.selectedOption() == EXIT_OPTION) {
            return false;
        }

        final EstadoOrdemProducao estado = selectorEstadoOrdem.selectedElement();

        Iterable<OrdemProducao> listaOrdens = theController.ordensProducaoComEstado(estado);

        if (((ArrayList<OrdemProducao>) listaOrdens).isEmpty()) {
            System.out.println("Não existem Ordens de Produção com o estado: " + estado.toString());
            return false;
        }

        System.out.println("Seleciona a Ordem de Produção:");
        final SelectWidget<OrdemProducao> selectorOrdem = new SelectWidget<>("", listaOrdens,
                new ListaOrdemProducaoPrinter());
        selectorOrdem.show();

        if (selectorOrdem.selectedOption() == EXIT_OPTION) {
            return false;
        }

        final OrdemProducao ordem = selectorOrdem.selectedElement();

        new OrdemProducaoPrinter().visit(ordem);

        if (estado == EstadoOrdemProducao.CONCLUIDA || estado == EstadoOrdemProducao.EXECUCAO) {
            ExecucaoOrdemProducao exec = theController.execOrdemProducao(ordem);
            System.out.println(" ");
            System.out.println("Produções:");
            System.out.printf("%-15s%-15s%-15s%-10s%-10s", "Data Registo", "Produto",
                    "Quantidade", "Desvio", "Depósito");
            for (Producao prod : exec.producoes()) {
                new ProducaoPrinter().visit(prod);
            }
            System.out.println(" ");
            System.out.println(" ");
            System.out.println("Consumos:");
            System.out.printf("%-15s%-15s%-15s%-10s%-10s", "Data Registo", "Código",
                    "Quantidade", "Desvio", "Depósito");
            for (Consumo cons : exec.consumos()) {
                new ConsumoPrinter().visit(cons);
            }
            System.out.println(" ");
            System.out.println(" ");
            System.out.println("Estornos:");
            System.out.printf("%-15s%-15s%-15s%-10s", "Data Registo", "Código",
                    "Quantidade", "Depósito");
            for (Estorno est : exec.estornos()) {
                new EstornoPrinter().visit(est);
            }
            System.out.println(" ");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Ordens de Produção com estado";
    }

}
