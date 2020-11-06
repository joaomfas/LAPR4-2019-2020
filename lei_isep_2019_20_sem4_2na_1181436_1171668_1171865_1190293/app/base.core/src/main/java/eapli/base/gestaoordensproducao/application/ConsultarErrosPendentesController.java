
package eapli.base.gestaoordensproducao.application;

import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import eapli.base.gestaoordensproducao.domain.ErroProcessamento;
import eapli.base.gestaoordensproducao.repositories.ErroProcessamentoRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.Controller;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class ConsultarErrosPendentesController implements Controller {
    
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ErroProcessamentoRepository repo = PersistenceContext.repositories()
            .erroProcessamento();
    
    public Iterable<ErroProcessamento> consultarErrosPendentes() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.GESTOR_CHAO_FABRICA);
        return repo.errosPendentes();
    }
    
    public Iterable<ErroProcessamento> consultarErrosPendentes(LinhaProducao linhaproducao) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.GESTOR_CHAO_FABRICA);
        return repo.errosPendentesPorLinha(linhaproducao);
    }
    
}
