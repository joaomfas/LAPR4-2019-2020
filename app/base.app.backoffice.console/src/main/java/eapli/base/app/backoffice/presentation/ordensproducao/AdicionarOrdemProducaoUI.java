package eapli.base.app.backoffice.presentation.ordensproducao;

import eapli.base.app.backoffice.presentation.produtos.OptionPrinter;
import eapli.base.app.backoffice.presentation.produtos.ProdutosPrinter;
import eapli.base.gestaoordensproducao.application.AdicionarOrdemProducaoController;
import eapli.base.gestaoprodutos.domain.Produto;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;
import java.util.ArrayList;
import java.util.List;

public class AdicionarOrdemProducaoUI extends AbstractUI {

    final int EXIT_OPTION = 0;
    final int ADD_ENCOMENDA_OPTION = 1;
    final int CONCLUIR_OPTION = 2;

    private final AdicionarOrdemProducaoController theController = new AdicionarOrdemProducaoController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        final Iterable<Produto> produtos = this.theController.allProdutos();

        final String codOrdemProducao = Console.readLine("Código da Ordem de Produção:");
        final String dataEmissao = Console.readLine("Data de Emissão da Ordem de Produção (DD/MM/AAAA):");
        final String dataPrevista = Console.readLine("Data Prevista de Conclusão da Ordem de Produção (DD/MM/AAAA):");

        System.out.printf("%-25s%-50s%-20s%-20s", "Código de Fabrico",
                "Descrição Completa", "Ficha de Produção", "Unidade de Medida");
        final SelectWidget<Produto> selector = new SelectWidget<>("", produtos,
                new ProdutosPrinter());
        selector.show();

        if (selector.selectedOption() == EXIT_OPTION) {
            return false;
        }

        final Produto produto = selector.selectedElement();

        Double quantidade = Double.parseDouble(Console.readLine("Quantidade (unidade de medida = "
                + produto.unidadeMedida().toString() + "):"));

        String codEncomenda = Console.readLine("Código da Encomenda:");
        try {
            this.theController.adicionarEncomenda(codEncomenda);
        } catch (@SuppressWarnings("unused") final Exception e) {
            System.out.println(e.getMessage());
        }

        int opcao = -1;

        List<String> options = new ArrayList<>();
        options.add("Adicionar encomenda");
        options.add("Concluir");

        while (opcao != CONCLUIR_OPTION) {
            final SelectWidget<String> selectorOption = new SelectWidget<>("Encomenda:", options,
                    new OptionPrinter());
            selectorOption.show();

            opcao = selectorOption.selectedOption();

            switch (opcao) {
                case ADD_ENCOMENDA_OPTION:
                    codEncomenda = Console.readLine("Código da Encomenda:");
                    try {
                        this.theController.adicionarEncomenda(codEncomenda);
                    } catch (@SuppressWarnings("unused") final Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case EXIT_OPTION:
                    return false;
                default:
                    break;
            }
        }

        try {
            this.theController.registarOrdemProducao(codOrdemProducao, dataEmissao, dataPrevista, produto, quantidade);
        } catch (@SuppressWarnings("unused") final Exception e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public String headline() {
        return "Registar Ordem de Produção";
    }

}
