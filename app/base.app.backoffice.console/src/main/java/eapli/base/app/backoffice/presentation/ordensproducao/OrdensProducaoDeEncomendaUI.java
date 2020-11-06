package eapli.base.app.backoffice.presentation.ordensproducao;

import eapli.base.gestaoordensproducao.application.OrdensProducaoDeEncomendaController;
import eapli.base.gestaoordensproducao.domain.Encomenda;
import eapli.base.gestaoordensproducao.domain.OrdemProducao;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;
import java.util.ArrayList;

/**
 *
 * @author mdias
 */
public class OrdensProducaoDeEncomendaUI extends AbstractUI {

    final int EXIT_OPTION = 0;

    private final OrdensProducaoDeEncomendaController theController = new OrdensProducaoDeEncomendaController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {

        final String codidoEncomenda = Console.readLine("Código da Encomenda a procurar:");
        Encomenda encomenda = new Encomenda(codidoEncomenda);
        final Iterable<OrdemProducao> ordensProducao = this.theController.ordensProducaoDeEncomenda(encomenda);

        if (((ArrayList<OrdemProducao>) ordensProducao).isEmpty()) {
            System.out.println("Não existem Ordens de Produção com a encomenda: " + encomenda.toString());
            return false;
        }

        System.out.println("Seleciona a Ordem de Produção:");
        final SelectWidget<OrdemProducao> selectorOrdem = new SelectWidget<>("", ordensProducao,
                new ListaOrdemProducaoPrinter());
        selectorOrdem.show();
        
        if (selectorOrdem.selectedOption() == EXIT_OPTION) {
            return false;
        }

        final OrdemProducao ordem = selectorOrdem.selectedElement();

        new OrdemProducaoPrinter().visit(ordem);

        return false;
    }

    @Override
    public String headline() {
        return "Ordens de Produção desta Encomenda";
    }

}
