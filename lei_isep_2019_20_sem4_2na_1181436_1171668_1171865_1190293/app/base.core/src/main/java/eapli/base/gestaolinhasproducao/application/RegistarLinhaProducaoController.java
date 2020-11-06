/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.gestaolinhasproducao.application;

import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import eapli.base.gestaolinhasproducao.repository.LinhaProducaoRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.Controller;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

/**
 *
 * @author 35193
 */

public class RegistarLinhaProducaoController implements Controller {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final LinhaProducaoRepository repository = PersistenceContext.repositories().linhasProducao();

    public LinhaProducao registarLinhaProducao(final String id) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.GESTOR_CHAO_FABRICA);

        try {
            final LinhaProducao linhaProducao = new LinhaProducao(id);
            return this.repository.save(linhaProducao);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
