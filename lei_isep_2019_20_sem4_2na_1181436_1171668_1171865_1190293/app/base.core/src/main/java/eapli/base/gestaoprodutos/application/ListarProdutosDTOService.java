package eapli.base.gestaoprodutos.application;

import eapli.base.gestaoprodutos.DTO.ProdutoDTO;
import eapli.base.gestaoprodutos.domain.Produto;
import eapli.base.gestaoprodutos.repositories.ProdutoRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import java.util.ArrayList;
import java.util.List;

public class ListarProdutosDTOService {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ProdutoRepository repo = PersistenceContext.repositories()
            .produtos();
    
    public Iterable<ProdutoDTO> produtosSemFicha() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER,
                BaseRoles.GESTOR_PRODUCAO);

        final Iterable<Produto> produtos = this.repo.produtosSemFichaProducao();

        final List<ProdutoDTO> ret = new ArrayList<>();
        produtos.forEach(e -> ret.add(e.toDTO()));
        return transformToDTO(produtos);
    }
    
    private Iterable<ProdutoDTO> transformToDTO(final Iterable<Produto> produtosSemFicha) {
        final List<ProdutoDTO> ret = new ArrayList<>();
        produtosSemFicha.forEach(e -> ret.add(e.toDTO()));
        return ret;
    }
}
