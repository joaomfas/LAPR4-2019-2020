package eapli.base.persistence.impl.inmemory;

import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import eapli.base.gestaolinhasproducao.repository.LinhaProducaoRepository;
import eapli.base.gestaomaquinas.domain.CodInterno;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryLinhaProducaoRepository extends InMemoryDomainRepository<String, LinhaProducao>
        implements LinhaProducaoRepository {
    
    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<LinhaProducao> linhaProducaoMaquina(CodInterno codInterno) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
  
}
