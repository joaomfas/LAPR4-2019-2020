package eapli.base.spm.application;

import eapli.base.gestaolinhasproducao.domain.DataProcessamento;
import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import eapli.base.gestaolinhasproducao.repository.LinhaProducaoRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import java.util.Calendar;

public class AtualizarProcessamentoService {
    
    private final LinhaProducaoRepository repository = PersistenceContext.repositories().linhasProducao();
    
    public synchronized void updateLinhaProducao(LinhaProducao lp) {
        LinhaProducao l = repository.ofIdentity(lp.identity()).get();
        l.updateDataProcessamento(new DataProcessamento(Calendar.getInstance().getTime()));
        repository.save(l);
    }

}
