package eapli.base.app.backoffice.presentation.produtos;

import eapli.base.gestaoprodutos.DTO.ProdutoDTO;
import eapli.base.gestaoprodutos.application.ApresentarProdutosSemFichaController;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;


public class ApresentarProdutosSemFichaUI extends AbstractListUI<ProdutoDTO>{

    private final ApresentarProdutosSemFichaController theController = new ApresentarProdutosSemFichaController();
    
    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected Iterable<ProdutoDTO> elements() {
        return this.theController.produtosSemFicha();
    }

    @Override
    protected Visitor<ProdutoDTO> elementPrinter() {
        return new ProdutoSemFichaDTOPrinter();
    }

    @Override
    protected String elementName() {
        return "Produtos sem ficha de produção";
    }

    @Override
    protected String listHeader() {
        return String.format("%-25s%-25s%-30s", "Código de Fabrico", "Código Comercial", "Descrição Completa");
    }
}
