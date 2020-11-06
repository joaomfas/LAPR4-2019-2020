package eapli.base.gestaoprodutos.application;

import eapli.base.gestaoprodutos.domain.Produto;
import eapli.base.gestaoprodutos.repositories.ProdutoRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class ListarProdutosService {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ProdutoRepository produtoRepository = PersistenceContext.repositories()
            .produtos();
    
    public Iterable<Produto> allProdutos() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.GESTOR_PRODUCAO);

        return this.produtoRepository.findAll();
    } 
    
    public Iterable<Produto> produtosSemFicha() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER,
                BaseRoles.GESTOR_PRODUCAO);

        return this.produtoRepository.produtosSemFichaProducao();
    }
}
