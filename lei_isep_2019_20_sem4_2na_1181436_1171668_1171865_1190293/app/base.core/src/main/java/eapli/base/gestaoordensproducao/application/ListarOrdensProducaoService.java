package eapli.base.gestaoordensproducao.application;

import eapli.base.gestaoordensproducao.domain.Encomenda;
import eapli.base.gestaoordensproducao.domain.EstadoOrdemProducao;
import eapli.base.gestaoordensproducao.domain.OrdemProducao;
import eapli.base.gestaoordensproducao.repositories.OrdemProducaoRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;


public class ListarOrdensProducaoService {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final OrdemProducaoRepository ordemProducaoRepository = PersistenceContext.repositories()
            .ordensProducao();
    
    public Iterable<OrdemProducao> allOrdensProducao() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.GESTOR_PRODUCAO);

        return this.ordemProducaoRepository.findAll();
    }
    
    public Iterable<OrdemProducao> ordensProducaoComEstado(EstadoOrdemProducao estado) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.GESTOR_PRODUCAO);

        return this.ordemProducaoRepository.ordensProducaoComEstado(estado);
    } 
    
    public Iterable<OrdemProducao> ordensProducaoDeEncomenda(Encomenda encomenda) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.GESTOR_PRODUCAO);

        return this.ordemProducaoRepository.ordensProducaoDeEncomenda(encomenda);
    }
    
}
