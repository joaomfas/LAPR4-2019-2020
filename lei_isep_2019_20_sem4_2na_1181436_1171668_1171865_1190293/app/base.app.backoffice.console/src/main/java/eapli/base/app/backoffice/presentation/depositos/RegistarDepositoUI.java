package eapli.base.app.backoffice.presentation.depositos;

import eapli.base.gestaodepositos.application.RegistarDepositoController;
import eapli.framework.application.Controller;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

public class RegistarDepositoUI extends AbstractUI{
    private final RegistarDepositoController theController = new RegistarDepositoController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        final String codigoDeposito = Console.readLine("Código Depósito:");
        final String descricao = Console.readLine("Descrição:");

        try {
            this.theController.registarDeposito(codigoDeposito, descricao);
        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("Erro ao registar depósito!");
        }
        return false;
    }

    @Override
    public String headline() {
        return "Regitar Depósito";
    }
}
