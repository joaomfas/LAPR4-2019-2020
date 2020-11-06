package eapli.base.app.backoffice.presentation.produtos;

import eapli.base.app.backoffice.presentation.materiasprimas.MateriaPrimaPrinter;
import eapli.base.gestaomateriasprimas.domain.MateriaPrima;
import eapli.base.gestaoprodutos.application.RegistarFichaProducaoController;
import eapli.base.gestaoprodutos.domain.Produto;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;
import java.util.ArrayList;
import java.util.List;

public class RegistarFichaProducaoUI extends AbstractUI {

    final int EXIT_OPTION = 0;
    final int PRODUTO_OPTION = 1;
    final int MP_OPTION = 2;
    final int CONCLUIR_OPTION = 3;

    private final RegistarFichaProducaoController theController = new RegistarFichaProducaoController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        final Iterable<Produto> produtos = this.theController.allProdutos();
        final Iterable<MateriaPrima> materiasPrimas = this.theController.allMateriasPrimas();

        if (((ArrayList<Produto>) produtos).isEmpty()) {
            System.out.println("Não existem produtos registados.");
            return false;
        }

        System.out.printf("%-25s%-50s%-20s%-20s", "Código de Fabrico",
                "Descrição Completa", "Ficha de Produção", "Unidade de Medida");
        final SelectWidget<Produto> selector = new SelectWidget<>("", produtos,
                new ProdutosPrinter());
        selector.show();

        if (selector.selectedOption() == EXIT_OPTION) {
            return false;
        }

        final Produto produto = selector.selectedElement();
        
        ((ArrayList<Produto>) produtos).remove(produto);

        int opcao = -1;

        List<String> options = new ArrayList<>();
        options.add("Produto");
        options.add("Matéria Prima");
        options.add("Concluir");

        while (opcao != CONCLUIR_OPTION) {
            final SelectWidget<String> selectorOption = new SelectWidget<>("Componente:", options,
                    new OptionPrinter());
            selectorOption.show();

            opcao = selectorOption.selectedOption();

            switch (opcao) {
                case PRODUTO_OPTION:
                    if (!adicionarProduto(theController, produtos)) {
                        return false;
                    }
                    break;
                case MP_OPTION:
                    if (!adicionarMateriaPrima(theController, materiasPrimas)) {
                        return false;
                    }
                    break;
                case EXIT_OPTION:
                    return false;
                default:
                    break;
            }
        }

        try {
            this.theController.registarFichaProducao(produto);
        } catch (@SuppressWarnings("unused") final Exception e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    private boolean adicionarProduto(RegistarFichaProducaoController theController, Iterable<Produto> produtos) {
        System.out.printf("%-25s%-30s%-20s%-20s", "Código de Fabrico",
                "Descrição Completa", "Ficha de Produção", "Unidade de Medida");
        final SelectWidget<Produto> selector = new SelectWidget<>("", produtos,
                new ProdutosPrinter());
        selector.show();

        if (selector.selectedOption() == EXIT_OPTION) {
            return false;
        }

        Produto produtoComponente = selector.selectedElement();

        if (produtoComponente != null) {
            final Double quantidade = Double.parseDouble(Console.readLine("Quantidade (unidade de medida = " + produtoComponente.unidadeMedida() + "):"));
            theController.adicionarProduto(produtoComponente, quantidade);
            return true;
        }

        return false;
    }

    private boolean adicionarMateriaPrima(RegistarFichaProducaoController theController, Iterable<MateriaPrima> materiasPrimas) {
        System.out.printf("%-25s%-25s%-30s%-20s", "Código", "Descrição",
                "Categoria", "Unidade de medida");
        final SelectWidget<MateriaPrima> selector = new SelectWidget<>("", materiasPrimas,
                new MateriaPrimaPrinter());
        selector.show();
        
        if (selector.selectedOption() == EXIT_OPTION) {
            return false;
        }

        MateriaPrima materiaPrimaComponente = selector.selectedElement();

        if (materiaPrimaComponente != null) {
            final Double quantidade = Double.parseDouble(Console.readLine("Quantidade (unidade de medida = " + materiaPrimaComponente.unidadeMedida() + "):"));
            theController.adicionarMateriaPrima(materiaPrimaComponente, quantidade);
            return true;
        }

        return false;
    }

    @Override
    public String headline() {
        return "Registar Ficha de Produção";
    }

}
