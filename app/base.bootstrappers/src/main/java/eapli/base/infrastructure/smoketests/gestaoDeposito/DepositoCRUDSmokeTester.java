package eapli.base.infrastructure.smoketests.gestaoDeposito;

import eapli.base.gestaodepositos.domain.CodigoDeposito;
import eapli.base.gestaodepositos.domain.Deposito;
import eapli.base.gestaodepositos.repository.DepositoRepository;
import eapli.base.gestaomaquinas.domain.Descricao;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.validations.Invariants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mdias
 */
public class DepositoCRUDSmokeTester {
    private static final Logger LOGGER = LogManager.getLogger(DepositoCRUDSmokeTester.class);

    private final DepositoRepository repo = PersistenceContext.repositories().depositos();

    public void testDepositoCRUD() {
        // save
        CodigoDeposito codDeposito1 = new CodigoDeposito("dep1in");
        Descricao desccricao1 = new Descricao("deposito1in");
        
        CodigoDeposito codDeposito2 = new CodigoDeposito("dep2out");
        Descricao desccricao2 = new Descricao("deposito2out");

        repo.save(new Deposito(codDeposito1, desccricao1));
        repo.save(new Deposito(codDeposito2, desccricao2));
        LOGGER.info("»»» depositos criados");

        // findAll
        final Iterable<Deposito> l = repo.findAll();
        Invariants.nonNull(l);
        Invariants.nonNull(l.iterator());
        Invariants.ensure(l.iterator().hasNext());
        LOGGER.info("»»» encontrar todos os depositos");

        // count
        final long n = repo.count();
        LOGGER.info("»»» # depositos = {}", n);

        // ofIdentity
        final Deposito dep1 = repo.ofIdentity(new CodigoDeposito("dep1in")).orElseThrow(IllegalStateException::new);
        final Deposito dep2 = repo.ofIdentity(new CodigoDeposito("dep2out")).orElseThrow(IllegalStateException::new);
        LOGGER.info("»»» encontrar depositos atraves da sua identidade");

        // containsOfIdentity
        final boolean hasId = repo.containsOfIdentity(dep1.identity());
        Invariants.ensure(hasId);
        LOGGER.info("»»» encontrar deposito que contenha identidade");

        // contains
        final boolean has = repo.contains(dep1);
        Invariants.ensure(has);
        LOGGER.info("»»» contem deposito");

        // delete
        repo.delete(dep1);
        LOGGER.info("»»» apagar deposito");

        // deleteOfIdentity
        repo.deleteOfIdentity(dep2.identity());
        LOGGER.info("»»» apagar deposito que contenha identidade");

        // size
        final long n2 = repo.size();
        Invariants.ensure(n2 == n - 2);
        LOGGER.info("»»» # deposito = {}", n2);
    }
}
