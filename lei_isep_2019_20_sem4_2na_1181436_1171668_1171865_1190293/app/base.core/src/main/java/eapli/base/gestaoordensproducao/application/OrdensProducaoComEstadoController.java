package eapli.base.gestaoordensproducao.application;

import eapli.base.gestaoordensproducao.domain.EstadoOrdemProducao;
import eapli.base.gestaoordensproducao.domain.ExecucaoOrdemProducao;
import eapli.base.gestaoordensproducao.domain.OrdemProducao;
import eapli.base.gestaoordensproducao.repositories.ExecucaoOrdemProducaoRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.Controller;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import java.util.ArrayList;

public class OrdensProducaoComEstadoController implements Controller {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ListarOrdensProducaoService svc = new ListarOrdensProducaoService();
    private final ExecucaoOrdemProducaoRepository execOPRepo = PersistenceContext.repositories().
            execucaoOrdensProducao();

    public EstadoOrdemProducao[] estadosOrdemProducao() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.GESTOR_PRODUCAO, BaseRoles.POWER_USER);
        return EstadoOrdemProducao.values();
    }

    public Iterable<OrdemProducao> ordensProducaoComEstado(EstadoOrdemProducao estado) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.GESTOR_PRODUCAO, BaseRoles.POWER_USER);
        return svc.ordensProducaoComEstado(estado);
    }
    
    public ExecucaoOrdemProducao execOrdemProducao(OrdemProducao ordemP){
        return ((ArrayList<ExecucaoOrdemProducao>) execOPRepo.execucaoDaOrdensProducao(ordemP)).get(0);
    }
}
