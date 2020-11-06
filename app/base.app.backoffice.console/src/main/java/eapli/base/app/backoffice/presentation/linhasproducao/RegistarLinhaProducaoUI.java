/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.app.backoffice.presentation.linhasproducao;


import eapli.framework.application.Controller;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import eapli.base.gestaolinhasproducao.application.RegistarLinhaProducaoController;
/**
 *
 * @author 35193
 */
public class RegistarLinhaProducaoUI extends AbstractUI {
    
    private final RegistarLinhaProducaoController theController = new RegistarLinhaProducaoController();

    protected Controller controller() {
        return this.theController;
    }
    
    
    @Override
    protected boolean doShow() {
        final String idLinhaProducao = Console.readLine("Id Linha producao:");

        try {
            this.theController.registarLinhaProducao(idLinhaProducao);
        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("Erro ao registar linha de producao!");
        }
        return false;
    }

    @Override
    public String headline() {
        return "Registar Linha Producao";
    }
}
