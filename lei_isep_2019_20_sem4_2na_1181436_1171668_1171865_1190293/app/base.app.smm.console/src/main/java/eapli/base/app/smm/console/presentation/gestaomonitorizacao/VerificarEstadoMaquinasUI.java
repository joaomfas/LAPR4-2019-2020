package eapli.base.app.smm.console.presentation.gestaomonitorizacao;

import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import eapli.base.gestaomaquinas.domain.Maquina;
import eapli.base.smm.server.SmmServer;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ListWidget;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class VerificarEstadoMaquinasUI extends AbstractUI {

    @Override
    protected boolean doShow() {
        List<Maquina> maquinas = new ArrayList();
        
        Set<LinhaProducao> linhasProducao = new SmmServer().linhasProducao();
        for(LinhaProducao l : linhasProducao) {
            maquinas.addAll(l.maquinas());
        }
        
        
        if(maquinas.isEmpty()) {
            //throw new IllegalArgumentException("Não existem linhas registadas.");
            System.out.println("Não existem maquinas registadas.");
            return false;
        }
        System.out.printf("%-30s %-30s %-30s", "Maquina", "IP", "Estado");
        final ListWidget<Maquina> list = new ListWidget<>("", maquinas, new MaquinaEstadoPrinter());
        list.show();
        
        return false;
    }

    @Override
    public String headline() {
        return "Estado das Maquinas";
    }
    
}
