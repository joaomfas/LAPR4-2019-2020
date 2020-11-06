package eapli.base.gestaomaquinas.application;

import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import eapli.base.gestaolinhasproducao.repository.LinhaProducaoRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class ListarLinhaProducaoService {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final LinhaProducaoRepository linhaProducaoRepository = PersistenceContext.repositories()
            .linhasProducao();

    public Iterable<LinhaProducao> todasLinhas() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER,
                BaseRoles.GESTOR_CHAO_FABRICA);

        return this.linhaProducaoRepository.findAll();
    }
    
    
}
