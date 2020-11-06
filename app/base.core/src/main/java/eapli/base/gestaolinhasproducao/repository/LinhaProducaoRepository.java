package eapli.base.gestaolinhasproducao.repository;

import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import eapli.base.gestaomaquinas.domain.CodInterno;
import eapli.framework.domain.repositories.DomainRepository;

public interface LinhaProducaoRepository extends DomainRepository<String, LinhaProducao> {
    
    public Iterable<LinhaProducao> linhaProducaoMaquina(CodInterno codInterno);
    
}
