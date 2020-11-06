/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.base.app.spm.console;

import eapli.base.app.spm.console.presentation.MainMenu;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;


/**
 *
 * @author Paulo Gandra Sousa
 */
@SuppressWarnings("squid:S106")
public final class SPMApp {
    
    /**
     * Empty constructor is private to avoid instantiation of this class.
     */
    private SPMApp() {
    }

    public static void main(final String[] args) {
        System.out.println("=====================================");
        System.out.println("Serviço de Processamento de Mensagens (SPM)");
        System.out.println("(C) 2020, LAPR4 - 2NA - G60");
        System.out.println("=====================================");

        AuthzRegistry.configure(PersistenceContext.repositories().users(),
                new BasePasswordPolicy(), new PlainTextEncoder());
             
        final MainMenu menu = new MainMenu();
        menu.mainLoop();

        System.exit(0);
    }
}
