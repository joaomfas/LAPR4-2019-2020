package eapli.base.gestaomateriasprimas.application;

import eapli.base.gestaomateriasprimas.domain.MateriaPrima;
import eapli.base.gestaomateriasprimas.repositories.MateriaPrimaRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class ListarMateriasPrimasService {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final MateriaPrimaRepository materiaPrimaRepository = PersistenceContext.repositories()
            .materiasPrimas();

    public Iterable<MateriaPrima> allMateriasPrimas() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.GESTOR_PRODUCAO);

        return this.materiaPrimaRepository.findAll();
    }
}
