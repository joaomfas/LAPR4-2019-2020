package eapli.base.gestaoordensproducao.application;

import eapli.base.gestaoordensproducao.domain.Encomenda;
import eapli.base.gestaoordensproducao.domain.OrdemProducao;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.application.Controller;

/**
 *
 * @author mdias
 */
public class OrdensProducaoDeEncomendaController implements Controller{
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ListarOrdensProducaoService svc = new ListarOrdensProducaoService();
    
    public Iterable<OrdemProducao> ordensProducaoDeEncomenda(Encomenda encomenda) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.GESTOR_PRODUCAO, BaseRoles.POWER_USER);
        return svc.ordensProducaoDeEncomenda(encomenda);
    }
}
