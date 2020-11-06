package eapli.base.gestaomateriasprimas.application;

import eapli.base.gestaomaquinas.domain.Descricao;
import eapli.base.gestaomateriasprimas.domain.CategoriaMateriaPrima;
import eapli.base.gestaomateriasprimas.domain.CodigoCategoria;
import eapli.base.gestaomateriasprimas.repositories.CategoriaMateriaPrimaRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.Controller;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class RegistarCategoriaMateriaPrimaController implements Controller {
    
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CategoriaMateriaPrimaRepository repository = PersistenceContext.repositories().categoriasMateriasPrimas();

    public CategoriaMateriaPrima registarCategoriaMateriaPrima(final String codigoCategoria, final String descricao) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.GESTOR_PRODUCAO, BaseRoles.POWER_USER);
        try {
            final CategoriaMateriaPrima newCategoriaMateriaPrima = new CategoriaMateriaPrima(new CodigoCategoria(codigoCategoria), new Descricao(descricao));
            return this.repository.save(newCategoriaMateriaPrima);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        
    }
    
}
