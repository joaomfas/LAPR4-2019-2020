package eapli.base.spm.application;

import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import eapli.base.gestaolinhasproducao.repository.LinhaProducaoRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;

public class IniciarProcessamentoRecorrenteController {
    
    LinhaProducaoRepository repository = PersistenceContext.repositories().linhasProducao();
    
    
    public void iniciarProcessamentoRecorrente() {
        
        Iterable<LinhaProducao> linhas = repository.findAll();
        
        new Thread(new ProcessamentoRecorrenteRunnable(linhas)).start();
        
    }
    
}
