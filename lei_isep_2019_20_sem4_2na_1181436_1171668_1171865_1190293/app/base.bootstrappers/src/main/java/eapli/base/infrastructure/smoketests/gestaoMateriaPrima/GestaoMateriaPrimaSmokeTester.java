package eapli.base.infrastructure.smoketests.gestaoMateriaPrima;

import eapli.base.gestaomateriasprimas.domain.MateriaPrima;
import eapli.base.gestaomateriasprimas.repositories.MateriaPrimaRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mdias
 */
public class GestaoMateriaPrimaSmokeTester implements Action {

    private static final Logger LOGGER = LogManager.getLogger(GestaoMateriaPrimaSmokeTester.class);
    private final MateriaPrimaCRUDSmokeTester MPCrudTester = new MateriaPrimaCRUDSmokeTester();
    private final CategoriaMateriaPrimaCRUDSmokeTester catMPCrudTester = new CategoriaMateriaPrimaCRUDSmokeTester();
    private final MateriaPrimaRepository materiaPrimaRepository = PersistenceContext.repositories().materiasPrimas();

    @Override
    public boolean execute() {
        try {
            testCategoriaMateriaPrimaCRUD();
            testMateriaPrimaCRUD();
            return true;
        } catch (FileNotFoundException ex) {
            System.out.println("Ficha técnica não existe!");
            //java.util.logging.Logger.getLogger(GestaoMateriaPrimaSmokeTester.class.getName()).log(Level.SEVERE, null, ex);
        }
        listarTodasMateriasPrimas();
        return true;
    }

    private void testMateriaPrimaCRUD() throws FileNotFoundException {
        MPCrudTester.testMateriaPrimaCRUD();
    }

    private void testCategoriaMateriaPrimaCRUD() {
        catMPCrudTester.testCategoriaMateriaPrimaCRUD();
    }

    private void listarTodasMateriasPrimas() {
        final Iterable<MateriaPrima> matprimas = materiaPrimaRepository.findAll();
        LOGGER.info("--- Materias Primas ---");
        for (final MateriaPrima mp : matprimas) {
            LOGGER.info("{}", mp.identity());
        }
    }
}
