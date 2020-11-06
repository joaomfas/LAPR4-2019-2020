package eapli.base.app.backoffice.presentation.produtos;

import eapli.base.gestaoprodutos.DTO.ProdutoDTO;
import eapli.framework.visitor.Visitor;

@SuppressWarnings("squid:S106")
public class ProdutoSemFichaDTOPrinter implements Visitor<ProdutoDTO> {

    @Override
    public void visit(ProdutoDTO visitee) {
        System.out.printf("%-25s%-25s%-30s", visitee.codigoFabrico, visitee.codigoComercial,
                visitee.descricaoCompleta);
    }
}
