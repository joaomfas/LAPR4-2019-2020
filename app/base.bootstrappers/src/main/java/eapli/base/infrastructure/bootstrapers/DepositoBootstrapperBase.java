package eapli.base.infrastructure.bootstrapers;

import eapli.base.gestaodepositos.application.RegistarDepositoController;
import eapli.base.gestaodepositos.domain.Deposito;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;

public class DepositoBootstrapperBase {
    final RegistarDepositoController registarDepositoController = new RegistarDepositoController();

    public DepositoBootstrapperBase() {
        super();
    }

    protected Deposito registarDeposito(final String codigoDeposito, final String descricao) {
        Deposito u = null;
        try {
            u = registarDepositoController.registarDeposito(codigoDeposito, descricao);
        } catch (final IntegrityViolationException | ConcurrencyException e) {

        }
        return u;
    }
}
