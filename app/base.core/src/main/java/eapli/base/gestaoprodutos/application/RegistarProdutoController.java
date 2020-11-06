package eapli.base.gestaoprodutos.application;

import eapli.base.gestaoprodutos.domain.Produto;
import eapli.base.gestaoprodutos.domain.ProdutoBuilder;
import eapli.base.gestaoprodutos.repositories.ProdutoRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.Controller;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

/**
 *
 * @author João Ferreira
 */
public class RegistarProdutoController implements Controller {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ProdutoRepository repository = PersistenceContext.repositories().produtos();

    public Produto registarProduto(final String codigoFabrico, final String codigoComercial,
            final String descricaoBreve, final String descricaoCompleta, final String categoria, final String unMedida) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.GESTOR_PRODUCAO, BaseRoles.POWER_USER);

        try {
            final ProdutoBuilder produtoBuilder = new ProdutoBuilder();
            produtoBuilder.comCodigoFabrico(codigoFabrico).comCodigoComercial(codigoComercial).comDescricaoBreve(descricaoBreve)
                    .comDescricaoCompleta(descricaoCompleta).comCategoria(categoria).comUnidadeMedida(unMedida);
            return this.repository.save(produtoBuilder.build());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
