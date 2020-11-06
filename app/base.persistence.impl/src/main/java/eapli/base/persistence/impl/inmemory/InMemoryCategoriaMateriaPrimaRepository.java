package eapli.base.persistence.impl.inmemory;

import eapli.base.gestaomateriasprimas.domain.CategoriaMateriaPrima;
import eapli.base.gestaomateriasprimas.domain.CodigoCategoria;
import eapli.base.gestaomateriasprimas.repositories.CategoriaMateriaPrimaRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryCategoriaMateriaPrimaRepository extends InMemoryDomainRepository<CodigoCategoria, CategoriaMateriaPrima>
        implements CategoriaMateriaPrimaRepository {

    static {
        InMemoryInitializer.init();
    }
    
}
