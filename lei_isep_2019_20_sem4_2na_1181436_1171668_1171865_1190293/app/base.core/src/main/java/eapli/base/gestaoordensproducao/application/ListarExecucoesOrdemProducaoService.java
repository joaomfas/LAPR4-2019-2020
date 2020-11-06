/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.gestaoordensproducao.application;

import eapli.base.gestaoordensproducao.domain.ExecucaoOrdemProducao;
import eapli.base.gestaoordensproducao.repositories.ExecucaoOrdemProducaoRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

/**
 *
 * @author mdias
 */
public class ListarExecucoesOrdemProducaoService {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ExecucaoOrdemProducaoRepository execucaoOrdemProducaoRepository = PersistenceContext.repositories().execucaoOrdensProducao();

    public Iterable<ExecucaoOrdemProducao> allExecucoesOrdemProducao() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.GESTOR_PRODUCAO);

        return this.execucaoOrdemProducaoRepository.findAll();
    }
}
