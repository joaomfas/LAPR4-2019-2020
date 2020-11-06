package eapli.base.infrastructure.smoketests.gestaolinhaproducao;

import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import eapli.base.gestaolinhasproducao.repository.LinhaProducaoRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.validations.Invariants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LinhaProducaoCRUDSmokeTester {
    
    private static final Logger LOGGER = LogManager.getLogger(LinhaProducaoCRUDSmokeTester.class);

    private final LinhaProducaoRepository repo = PersistenceContext.repositories().linhasProducao();

    public void testLinhaProducaoCRUD() {
        
        String id1 = "510";
        repo.save(new LinhaProducao(id1));
        String id2 = "520";
        repo.save(new LinhaProducao(id2));
        String id3 = "530";
        repo.save(new LinhaProducao(id3));
        LOGGER.info("»»» Linhas de Produção criadas");
        
        // findAll
        final Iterable<LinhaProducao> l = repo.findAll();
        Invariants.nonNull(l);
        Invariants.nonNull(l.iterator());
        Invariants.ensure(l.iterator().hasNext());
        LOGGER.info("»»» encontrar todos os Linhas de Produção");

        // count
        final long n = repo.count();
        LOGGER.info("»»» # Linhas Producao = {}", n);
        
        
        // ofIdentity
        final LinhaProducao l1 = repo.ofIdentity(id1).orElseThrow(IllegalStateException::new);
        final LinhaProducao l2 = repo.ofIdentity(id2).orElseThrow(IllegalStateException::new);
        LOGGER.info("»»» encontrar Linhas de Produção através da sua identidade");

        // containsOfIdentity
        final boolean hasId = repo.containsOfIdentity(l1.identity());
        Invariants.ensure(hasId);
        LOGGER.info("»»» encontrar Linhas de Produção que contenha identidade");
        
        // contains
        final boolean has = repo.contains(l1);
        Invariants.ensure(has);
        LOGGER.info("»»» contém Linha de Produção");

        // delete
        repo.delete(l1);
        LOGGER.info("»»» apagar Linha de Produção");

        // deleteOfIdentity
        repo.deleteOfIdentity(l2.identity());
        LOGGER.info("»»» apagar Linha de Produção que contenha identidade");

        // size
        final long n2 = repo.size();
        Invariants.ensure(n2 == n - 2);
        LOGGER.info("»»» # Linha de Produção = {}", n2);
        
        
    }
    
    
    
    
}
