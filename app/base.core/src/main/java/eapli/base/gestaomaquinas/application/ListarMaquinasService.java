package eapli.base.gestaomaquinas.application;

import eapli.base.gestaomaquinas.domain.Maquina;
import eapli.base.gestaomaquinas.repositories.MaquinaRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class ListarMaquinasService {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();    
    private final MaquinaRepository repo = PersistenceContext.repositories().maquinas();
    
    public Iterable<Maquina> todasMaquinas() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.GESTOR_CHAO_FABRICA, BaseRoles.GESTOR_PRODUCAO);
        
        return repo.findAll();
    }
    
    
}
