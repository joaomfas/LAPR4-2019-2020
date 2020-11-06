package eapli.base.gestaoprodutos.application;

import eapli.base.gestaoprodutos.DTO.ProdutoDTO;
import eapli.framework.application.Controller;

public class ListarProdutosDTOController implements Controller {

    private final ListarProdutosDTOService svc = new ListarProdutosDTOService();

    public Iterable<ProdutoDTO> produtosSemFichaProducao() {
        return this.svc.produtosSemFicha();
    }
}
