package eapli.base.persistence.impl.inmemory;

import eapli.base.gestaodepositos.domain.CodigoDeposito;
import eapli.base.gestaodepositos.domain.Deposito;
import eapli.base.gestaodepositos.repository.DepositoRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryDepositoRepository extends InMemoryDomainRepository<CodigoDeposito, Deposito>
        implements DepositoRepository {

    static {
        InMemoryInitializer.init();
    }
    
}
