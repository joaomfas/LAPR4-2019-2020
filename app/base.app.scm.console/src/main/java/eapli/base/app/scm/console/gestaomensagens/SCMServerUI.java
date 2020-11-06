package eapli.base.app.scm.console.gestaomensagens;

import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import eapli.base.gestaomensagens.application.SCMServerController;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import java.util.ArrayList;


public class SCMServerUI extends AbstractUI {

    private final SCMServerController theController = new SCMServerController();

    protected Controller controller() {
        return this.theController;
    }
    
    @Override
    protected boolean doShow() {
        final Iterable<LinhaProducao> linhasProducao = this.theController.linhasProducao();
        
        if(((ArrayList<LinhaProducao>)linhasProducao).isEmpty()) {
            throw new IllegalArgumentException("Não existem linhas registadas.");
        }
        
        try {
            theController.startSCMServer();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public String headline() {
        return "SCM Server";
    }

}
