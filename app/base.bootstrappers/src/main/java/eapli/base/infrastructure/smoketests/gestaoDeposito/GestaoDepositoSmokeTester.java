package eapli.base.infrastructure.smoketests.gestaoDeposito;

import eapli.base.gestaodepositos.domain.Deposito;
import eapli.base.gestaodepositos.repository.DepositoRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mdias
 */
public class GestaoDepositoSmokeTester implements Action{
    private static final Logger LOGGER = LogManager.getLogger(GestaoDepositoSmokeTester.class);
    private final DepositoCRUDSmokeTester crudTester = new DepositoCRUDSmokeTester();
    private final DepositoRepository depositoRepository = PersistenceContext.repositories().depositos();

    @Override
    public boolean execute() {
        testDepositoCRUD();
        listarTodosDepositos();
        return true;
    }

    private void testDepositoCRUD() {
        crudTester.testDepositoCRUD();
    }

    private void listarTodosDepositos() {
        final Iterable<Deposito> depositos = depositoRepository.findAll();
        LOGGER.info("--- DEPOSITOS ---");
        for (final Deposito dep : depositos) {
            LOGGER.info("{}", dep.identity());
        }
    }
}
