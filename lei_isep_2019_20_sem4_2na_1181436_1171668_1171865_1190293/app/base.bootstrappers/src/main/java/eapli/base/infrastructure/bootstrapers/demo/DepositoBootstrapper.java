package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.infrastructure.bootstrapers.DepositoBootstrapperBase;
import eapli.framework.actions.Action;

public class DepositoBootstrapper extends DepositoBootstrapperBase implements Action {

    @Override
    public boolean execute() {
        this.registarDeposito("dpst1001", "Deposito A");
        this.registarDeposito("dpst1002", "Deposito B");
        this.registarDeposito("dpst1003", "Deposito C");
        this.registarDeposito("dpst1004", "Deposito D");
        this.registarDeposito("dpst1005", "Deposito E");
        this.registarDeposito("L040", "L040");
        this.registarDeposito("L042", "L042");
        this.registarDeposito("L030", "L030");
        return true;
    }
    
}
