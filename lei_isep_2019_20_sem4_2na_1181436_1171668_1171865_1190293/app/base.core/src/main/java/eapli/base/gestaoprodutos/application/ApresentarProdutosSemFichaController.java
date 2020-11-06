package eapli.base.gestaoprodutos.application;

import eapli.base.gestaoprodutos.DTO.ProdutoDTO;
import eapli.framework.application.Controller;

/**
 *
 * @author João Ferreira
 */
public class ApresentarProdutosSemFichaController implements Controller {

    private final ListarProdutosDTOService svc = new ListarProdutosDTOService();

    public Iterable<ProdutoDTO> produtosSemFicha() {
        return this.svc.produtosSemFicha();
    }
}
