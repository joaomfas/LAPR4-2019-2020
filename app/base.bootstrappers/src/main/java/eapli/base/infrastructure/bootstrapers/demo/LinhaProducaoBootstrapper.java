package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.infrastructure.bootstrapers.LinhaProducaoBootstrapperBase;
import eapli.framework.actions.Action;

public class LinhaProducaoBootstrapper extends LinhaProducaoBootstrapperBase implements Action {

    @Override
    public boolean execute() {
        registarLinhaProducao("10");
        registarLinhaProducao("20");
        registarLinhaProducao("30");
        return true;
    }
    
}
