/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.base.app.smm.console;

//import eapli.base.app.common.console.presentation.authz.LoginAction;
import eapli.base.app.smm.console.presentation.MainMenu;
import eapli.base.infrastructure.persistence.PersistenceContext;

import eapli.base.usermanagement.domain.BasePasswordPolicy;
//import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;

/**
 *
 * @author Paulo Gandra Sousa
 */
@SuppressWarnings("squid:S106")
public final class SMMServer {

    /**
     * Empty constructor is private to avoid instantiation of this class.
     */
    private SMMServer() {
    }

    public static void main(final String[] args) {
        System.out.println("=====================================");
        System.out.println("Servicp Monitorização das Maquinas (SMM)");
        System.out.println("(C) 2020, LAPR4 - 2NA - G60");
        System.out.println("=====================================");

        AuthzRegistry.configure(PersistenceContext.repositories().users(),
                new BasePasswordPolicy(), new PlainTextEncoder());

        
        // go to main menu
        final MainMenu menu = new MainMenu();
        menu.mainLoop();

        // exiting the application, closing all threads
        System.exit(0);
    }


}
