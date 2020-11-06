package eapli.base.infrastructure.smoketests.gestaoordensproducao;

import eapli.base.gestaoordensproducao.domain.CodigoOrdemProducao;
import eapli.base.gestaoordensproducao.domain.DataEmissao;
import eapli.base.gestaoordensproducao.domain.DataPrevista;
import eapli.base.gestaoordensproducao.domain.Encomenda;
import eapli.base.gestaoordensproducao.domain.OrdemProducao;
import eapli.base.gestaoordensproducao.repositories.OrdemProducaoRepository;
import eapli.base.gestaoprodutos.domain.CodigoComercial;
import eapli.base.gestaoprodutos.domain.CodigoFabrico;
import eapli.base.gestaoprodutos.domain.DescricaoBreve;
import eapli.base.gestaoprodutos.domain.DescricaoCompleta;
import eapli.base.gestaoprodutos.domain.Produto;
import eapli.base.gestaoprodutos.domain.UnidadeMedida;
import eapli.base.gestaoprodutos.repositories.ProdutoRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.validations.Invariants;
import java.util.HashSet;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OrdemProducaoCRUDTester {

    private static final Logger LOGGER = LogManager.getLogger(OrdemProducaoCRUDTester.class);

    private final OrdemProducaoRepository repo = PersistenceContext.repositories().ordensProducao();
    private final ProdutoRepository produtoRepo = PersistenceContext.repositories().produtos();

    public void testOrdemProducaoCRUD() {
        // save
        CodigoOrdemProducao numOrdemProducao = new CodigoOrdemProducao("ADASDAD");
        Produto produto = new Produto(new CodigoFabrico("123"), new CodigoComercial("123"),
                new DescricaoBreve("desc"), new DescricaoCompleta("desc"), "cat", new UnidadeMedida("un"));
        Double quantidade = 20D;
        DataEmissao dataEmissao = new DataEmissao("15/05/2020");
        DataPrevista dataPrevista = new DataPrevista("15/12/2022");
        Set<Encomenda> encomendas = new HashSet<>();
        encomendas.add(new Encomenda("AASDA"));

        CodigoOrdemProducao numOrdemProducao1 = new CodigoOrdemProducao("ALASJSIF");
        Double quantidade1 = 30D;
        DataEmissao dataEmissao1 = new DataEmissao("14/05/2020");
        DataPrevista dataPrevista1 = new DataPrevista("15/11/2022");
        Set<Encomenda> encomendas1 = new HashSet<>();
        encomendas1.add(new Encomenda("ALALAAOSOS"));
        
        produtoRepo.save(produto);
        Produto prod1 = produtoRepo.ofIdentity(new CodigoFabrico("123")).orElseThrow(IllegalStateException::new);
        
        repo.save(new OrdemProducao(numOrdemProducao, dataEmissao, prod1, quantidade, dataPrevista, encomendas));
        repo.save(new OrdemProducao(numOrdemProducao1, dataEmissao1, prod1, quantidade1, dataPrevista1, encomendas1));
        LOGGER.info("»»» ordens de produção criadas");

        // findAll
        final Iterable<OrdemProducao> l = repo.findAll();

        Invariants.nonNull(l);

        Invariants.nonNull(l.iterator());
        Invariants.ensure(l.iterator().hasNext());
        LOGGER.info("»»» encontrar todas as ordens de produção");

        // count
        final long n = repo.count();

        LOGGER.info("»»» # ordens de produção = {}", n);

        // ofIdentity
        final OrdemProducao p1 = repo.ofIdentity(new CodigoOrdemProducao("ADASDAD")).orElseThrow(IllegalStateException::new);
        final OrdemProducao p2 = repo.ofIdentity(new CodigoOrdemProducao("ALASJSIF")).orElseThrow(IllegalStateException::new);

        LOGGER.info("»»» encontrar ordens de produção através da sua identidade");

        // containsOfIdentity
        final boolean hasId = repo.containsOfIdentity(new CodigoOrdemProducao("ALASJSIF"));

        Invariants.ensure(hasId);

        LOGGER.info("»»» encontrar ordens de produção que contenha identidade");

        // contains
        final boolean has = repo.contains(p1);

        Invariants.ensure(has);

        LOGGER.info("»»» contém ordem de produção");

        // delete
        repo.delete(p1);

        LOGGER.info("»»» apagar ordem de produção");

        // size
        final long n2 = repo.size();

        Invariants.ensure(n2 == n - 1);
        LOGGER.info(
                "»»» # ordem de produção = {}", n2);
    }
}
