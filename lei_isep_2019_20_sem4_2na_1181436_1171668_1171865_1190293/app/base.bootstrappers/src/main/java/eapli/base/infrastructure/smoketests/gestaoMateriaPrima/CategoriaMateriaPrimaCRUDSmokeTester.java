package eapli.base.infrastructure.smoketests.gestaoMateriaPrima;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eapli.base.gestaomaquinas.domain.Descricao;
import eapli.base.gestaomateriasprimas.domain.CategoriaMateriaPrima;
import eapli.base.gestaomateriasprimas.domain.CodigoCategoria;
import eapli.base.gestaomateriasprimas.repositories.CategoriaMateriaPrimaRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.validations.Invariants;

public class CategoriaMateriaPrimaCRUDSmokeTester {

    private static final Logger LOGGER = LogManager.getLogger(CategoriaMateriaPrimaCRUDSmokeTester.class);
    private final CategoriaMateriaPrimaRepository catMPrepo = PersistenceContext.repositories().categoriasMateriasPrimas();

    public void testCategoriaMateriaPrimaCRUD() {
        // save
        CodigoCategoria codcat1 = new CodigoCategoria("1000001");
        Descricao desc1 = new Descricao("desc1");
        CategoriaMateriaPrima catmp1 = new CategoriaMateriaPrima(codcat1, desc1);

        CodigoCategoria codcat2 = new CodigoCategoria("1000002");
        Descricao desc2 = new Descricao("desc2");
        CategoriaMateriaPrima catmp2 = new CategoriaMateriaPrima(codcat2, desc2);

        catMPrepo.save(catmp1);
        catMPrepo.save(catmp2);
        LOGGER.info("»»» categorias de materias primas criadas");

        // findAll
        final Iterable<CategoriaMateriaPrima> l = catMPrepo.findAll();
        Invariants.nonNull(l);
        Invariants.nonNull(l.iterator());
        Invariants.ensure(l.iterator().hasNext());
        LOGGER.info("»»» encontrar todas as categorias de materias primas");

        // count
        final long n = catMPrepo.count();
        LOGGER.info("»»» # categorias de materias primas = {}", n);

        // ofIdentity
        final CategoriaMateriaPrima cat1 = catMPrepo.ofIdentity(new CodigoCategoria("1000001")).orElseThrow(IllegalStateException::new);
        final CategoriaMateriaPrima cat2 = catMPrepo.ofIdentity(new CodigoCategoria("1000002")).orElseThrow(IllegalStateException::new);
        LOGGER.info("»»» encontrar categorias de materias primas atraves da sua identidade");

        // containsOfIdentity
        final boolean hasId = catMPrepo.containsOfIdentity(cat1.identity());
        Invariants.ensure(hasId);
        LOGGER.info("»»» encontrar categoria de materia prima que contenha identidade");

        // contains
        final boolean has = catMPrepo.contains(cat1);
        Invariants.ensure(has);
        LOGGER.info("»»» contem categoria de materia prima");

        // delete
        catMPrepo.delete(cat1);
        LOGGER.info("»»» apagar categoria de materia prima");

        // deleteOfIdentity
        catMPrepo.deleteOfIdentity(cat2.identity());
        LOGGER.info("»»» apagar categoria de materia prima que contenha identidade");

        // size
        final long n2 = catMPrepo.size();
        Invariants.ensure(n2 == n - 2);
        LOGGER.info("»»» # materia prima = {}", n2);
    }
}
