package eapli.base.persistence.impl.inmemory;

import eapli.base.gestaomateriasprimas.domain.CodigoInterno;
import eapli.base.gestaomateriasprimas.domain.MateriaPrima;
import eapli.base.gestaomateriasprimas.repositories.MateriaPrimaRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryMateriaPrimaRepository extends InMemoryDomainRepository<CodigoInterno, MateriaPrima>
        implements MateriaPrimaRepository {

    static {
        InMemoryInitializer.init();
    }
    
}
