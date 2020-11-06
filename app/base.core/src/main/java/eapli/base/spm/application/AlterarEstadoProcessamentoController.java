package eapli.base.spm.application;

import eapli.base.gestaolinhasproducao.application.ListarLinhasProducaoService;
import eapli.base.gestaolinhasproducao.domain.EstadoProcessamentoRecorrente;
import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import eapli.base.gestaolinhasproducao.repository.LinhaProducaoRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.Controller;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

/**
 *
 * @author mdias
 */
public class AlterarEstadoProcessamentoController implements Controller {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ListarLinhasProducaoService svc = new ListarLinhasProducaoService();
    private final LinhaProducaoRepository repository = PersistenceContext.repositories().linhasProducao();

    public boolean alterarEstadoProcessamento(final String idLinhaProducao, EstadoProcessamentoRecorrente estado) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.GESTOR_CHAO_FABRICA, BaseRoles.POWER_USER);
        try {
            final LinhaProducao linhaProducao = this.repository.ofIdentity(idLinhaProducao).orElseThrow(IllegalStateException::new);
            linhaProducao.updateEstadoProcessamentoRecorrente(estado);
            this.repository.save(linhaProducao);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Iterable<LinhaProducao> linhasProducao() {
        return this.svc.allLinhasProducao();
    }

    public EstadoProcessamentoRecorrente[] estadosProcessamentoRecorrente() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.GESTOR_CHAO_FABRICA, BaseRoles.POWER_USER);
        return EstadoProcessamentoRecorrente.values();
    }
}
