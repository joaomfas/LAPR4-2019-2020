package eapli.base.app.backoffice.presentation.produtos;

import eapli.base.gestaoprodutos.domain.Produto;
import eapli.framework.visitor.Visitor;


@SuppressWarnings("squid:S106")
public class ProdutosPrinter implements Visitor<Produto> {

    @Override
    public void visit(Produto visitee) {
        System.out.printf("%-25s%-50s%-20s%-20s", visitee.identity(),
                visitee.descricaoCompleta(), visitee.hasFichaProducao(), visitee.unidadeMedida().toString());
    }
}
