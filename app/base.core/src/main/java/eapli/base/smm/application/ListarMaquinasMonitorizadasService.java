package eapli.base.smm.application;

import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import eapli.base.gestaomaquinas.domain.Maquina;
import eapli.base.smm.server.SmmServer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class ListarMaquinasMonitorizadasService {
    
    public List<Maquina> maquinasMonitorizadas() {
        List<Maquina> maquinasMonitorizadas = new ArrayList();
        SmmServer smmServer = new SmmServer();
        Set<LinhaProducao> linhasP = smmServer.linhasProducao();
        for(LinhaProducao l:linhasP) 
            maquinasMonitorizadas.addAll(l.maquinasMonitorizadas());
        return maquinasMonitorizadas;
    }
    
}
