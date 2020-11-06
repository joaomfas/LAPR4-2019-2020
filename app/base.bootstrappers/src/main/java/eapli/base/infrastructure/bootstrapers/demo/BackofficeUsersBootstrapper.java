/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.base.infrastructure.bootstrapers.demo;

import java.util.HashSet;
import java.util.Set;

import eapli.base.infrastructure.bootstrapers.UsersBootstrapperBase;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.domain.model.Role;

/**
 * @author Paulo Gandra Sousa
 */
public class BackofficeUsersBootstrapper extends UsersBootstrapperBase implements Action {

    @SuppressWarnings("squid:S2068")
    private static final String PASSWORD1 = "Password1";

    @Override
    public boolean execute() {
        registarGestorProducao("gestor", PASSWORD1, "Gestor", "Producao", "gestor@email.com");
        registarGestorChaoFabrica("chaofabrica", PASSWORD1, "fabrica", "chaofabrica", "chaofabrica@email.com");
        return true;
    }

    private void registarGestorProducao(final String username, final String password,
            final String firstName, final String lastName, final String email) {
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.GESTOR_PRODUCAO);
        registerUser(username, password, firstName, lastName, email, roles);
    }
    
    private void registarGestorChaoFabrica(final String username, final String password,
            final String firstName, final String lastName, final String email) {
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.GESTOR_CHAO_FABRICA);

        registerUser(username, password, firstName, lastName, email, roles);
    }
    
}
