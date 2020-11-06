package eapli.base.gestaomateriasprimas.application;

import eapli.base.gestaomateriasprimas.domain.CategoriaMateriaPrima;
import eapli.base.gestaomateriasprimas.repositories.CategoriaMateriaPrimaRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class ListarCategoriaMateriaPrimaService {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CategoriaMateriaPrimaRepository categoriaMateriaPrimaRepository = PersistenceContext.repositories()
            .categoriasMateriasPrimas();

    public Iterable<CategoriaMateriaPrima> allCategoriasMateriasPrimas() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.GESTOR_PRODUCAO);

        return this.categoriaMateriaPrimaRepository.findAll();
    } 
    
}
