package eapli.base.infrastructure.smoketests.gestaoProdutos;

import eapli.base.gestaoprodutos.domain.CodigoComercial;
import eapli.base.gestaoprodutos.domain.CodigoFabrico;
import eapli.base.gestaoprodutos.domain.DescricaoBreve;
import eapli.base.gestaoprodutos.domain.DescricaoCompleta;
import eapli.base.gestaoprodutos.domain.Produto;
import eapli.base.gestaoprodutos.domain.UnidadeMedida;
import eapli.base.gestaoprodutos.repositories.ProdutoRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.validations.Invariants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProdutoCRUDSmokeTester {

    private static final Logger LOGGER = LogManager.getLogger(ProdutoCRUDSmokeTester.class);

    private final ProdutoRepository repo = PersistenceContext.repositories().produtos();

    public void testProdutoCRUD() {
        // save
        CodigoFabrico codFabrico = new CodigoFabrico("123");
        CodigoComercial codComercial = new CodigoComercial("1002");
        DescricaoBreve descBreve = new DescricaoBreve("desc");
        DescricaoCompleta descCompleta = new DescricaoCompleta("descCompleta");
        String cat = "cat";
        UnidadeMedida unMedida = new UnidadeMedida("kg");

        CodigoFabrico codFabrico1 = new CodigoFabrico("987");
        CodigoComercial codComercial1 = new CodigoComercial("1001");
        DescricaoBreve descBreve1 = new DescricaoBreve("desc");
        DescricaoCompleta descCompleta1 = new DescricaoCompleta("descCompleta");
        String cat1 = "cat";
        UnidadeMedida unMedida1 = new UnidadeMedida("kg");

        repo.save(new Produto(codFabrico, codComercial, descBreve, descCompleta, cat, unMedida));
        repo.save(new Produto(codFabrico1, codComercial1, descBreve1, descCompleta1, cat1, unMedida1));
        LOGGER.info("»»» produtos criados");

        // findAll
        final Iterable<Produto> l = repo.findAll();
        Invariants.nonNull(l);
        Invariants.nonNull(l.iterator());
        Invariants.ensure(l.iterator().hasNext());
        LOGGER.info("»»» encontrar todos os produtos");

        // count
        final long n = repo.count();
        LOGGER.info("»»» # produtos = {}", n);

        // ofIdentity
        final Produto p1 = repo.ofIdentity(new CodigoFabrico("123")).orElseThrow(IllegalStateException::new);
        final Produto p2 = repo.ofIdentity(new CodigoFabrico("987")).orElseThrow(IllegalStateException::new);
        LOGGER.info("»»» encontrar produtos através da sua identidade");

        // containsOfIdentity
        final boolean hasId = repo.containsOfIdentity(p1.identity());
        Invariants.ensure(hasId);
        LOGGER.info("»»» encontrar produto que contenha identidade");

        // contains
        final boolean has = repo.contains(p1);
        Invariants.ensure(has);
        LOGGER.info("»»» contém produto");

        // delete
        repo.delete(p1);
        LOGGER.info("»»» apagar produto");

        // deleteOfIdentity
        repo.deleteOfIdentity(p2.identity());
        LOGGER.info("»»» apagar produto que contenha identidade");

        // size
        final long n2 = repo.size();
        Invariants.ensure(n2 == n - 2);
        LOGGER.info("»»» # produto = {}", n2);
    }
}
