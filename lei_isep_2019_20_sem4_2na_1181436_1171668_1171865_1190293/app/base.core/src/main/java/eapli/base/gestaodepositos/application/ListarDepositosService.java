package eapli.base.gestaodepositos.application;

import eapli.base.gestaodepositos.domain.Deposito;
import eapli.base.gestaodepositos.repository.DepositoRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

/**
 *
 * @author mdias
 */
public class ListarDepositosService {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final DepositoRepository depositoRepository = PersistenceContext.repositories().depositos();
    
    public Iterable<Deposito> allDepositos() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.GESTOR_PRODUCAO);

        return this.depositoRepository.findAll();
    }
    
}
