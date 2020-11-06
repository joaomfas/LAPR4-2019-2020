package eapli.base.infrastructure.smoketests.gestaoMateriaPrima;

import eapli.base.gestaomaquinas.domain.Descricao;
import eapli.base.gestaomateriasprimas.domain.CategoriaMateriaPrima;
import eapli.base.gestaomateriasprimas.domain.CodigoCategoria;
import eapli.base.gestaomateriasprimas.domain.CodigoInterno;
import eapli.base.gestaomateriasprimas.domain.FichaTecnica;
import eapli.base.gestaomateriasprimas.domain.MateriaPrima;
import eapli.base.gestaomateriasprimas.repositories.CategoriaMateriaPrimaRepository;
import eapli.base.gestaomateriasprimas.repositories.MateriaPrimaRepository;
import eapli.base.gestaoprodutos.domain.UnidadeMedida;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.validations.Invariants;
import java.io.FileNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mdias
 */
public class MateriaPrimaCRUDSmokeTester {
    private static final Logger LOGGER = LogManager.getLogger(MateriaPrimaCRUDSmokeTester.class);

    private final MateriaPrimaRepository repo = PersistenceContext.repositories().materiasPrimas();
    private final CategoriaMateriaPrimaRepository catMPrepo = PersistenceContext.repositories().categoriasMateriasPrimas();

    public void testMateriaPrimaCRUD() throws FileNotFoundException {
        // save
        CodigoInterno codInterno1 = new CodigoInterno("200001");
        CategoriaMateriaPrima catMateriaPrima1 = new CategoriaMateriaPrima(new CodigoCategoria("100002"),new Descricao("Madeira"));
        catMPrepo.save(catMateriaPrima1);
        Descricao desccricao1 = new Descricao("Tabua");
        FichaTecnica fichaTecnica1 = new FichaTecnica(System.getProperty("user.dir") + "/ficheirosTeste/ft_cortica.pdf");
        UnidadeMedida unMedida1 = new UnidadeMedida("un");

        CodigoInterno codInterno2 = new CodigoInterno("200004");
        CategoriaMateriaPrima catMateriaPrima2 = new CategoriaMateriaPrima(new CodigoCategoria("100004"),new Descricao("Textil"));
        catMPrepo.save(catMateriaPrima2);
        Descricao desccricao2 = new Descricao("Tecido almofadado");
        FichaTecnica fichaTecnica2 = new FichaTecnica(System.getProperty("user.dir") + "/ficheirosTeste/ft_cortica.pdf");
        UnidadeMedida unMedida2 = new UnidadeMedida("m");

        CategoriaMateriaPrima catmp1 = catMPrepo.ofIdentity(new CodigoCategoria("100002")).orElseThrow(IllegalStateException::new);
        CategoriaMateriaPrima catmp2 = catMPrepo.ofIdentity(new CodigoCategoria("100004")).orElseThrow(IllegalStateException::new);
        
        repo.save(new MateriaPrima(codInterno1, catmp1, desccricao1, fichaTecnica1, unMedida1));
        repo.save(new MateriaPrima(codInterno2, catmp2, desccricao2, fichaTecnica2, unMedida2));
        LOGGER.info("»»» materias primas criadas");

        // findAll
        final Iterable<MateriaPrima> l = repo.findAll();
        Invariants.nonNull(l);
        Invariants.nonNull(l.iterator());
        Invariants.ensure(l.iterator().hasNext());
        LOGGER.info("»»» encontrar todas as materias primas");

        // count
        final long n = repo.count();
        LOGGER.info("»»» # materias primas = {}", n);

        // ofIdentity
        final MateriaPrima mp1 = repo.ofIdentity(new CodigoInterno("200001")).orElseThrow(IllegalStateException::new);
        final MateriaPrima mp2 = repo.ofIdentity(new CodigoInterno("200004")).orElseThrow(IllegalStateException::new);
        LOGGER.info("»»» encontrar materias primas atraves da sua identidade");

        // containsOfIdentity
        final boolean hasId = repo.containsOfIdentity(mp1.identity());
        Invariants.ensure(hasId);
        LOGGER.info("»»» encontrar materia prima que contenha identidade");

        // contains
        final boolean has = repo.contains(mp1);
        Invariants.ensure(has);
        LOGGER.info("»»» contem materia prima");

        // delete
        repo.delete(mp1);
        LOGGER.info("»»» apagar materia prima");

        // deleteOfIdentity
        repo.deleteOfIdentity(mp2.identity());
        LOGGER.info("»»» apagar materia prima que contenha identidade");

        // size
        final long n2 = repo.size();
        Invariants.ensure(n2 == n - 2);
        LOGGER.info("»»» # materia prima = {}", n2);
    }
}
