package eapli.base.infrastructure.smoketests.gestaoProdutos;

import eapli.base.gestaoprodutos.application.ImportarProdutosCSVController;
import eapli.base.gestaoprodutos.domain.Produto;
import eapli.base.gestaoprodutos.repositories.ProdutoRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GestaoProdutosSmokeTester implements Action {

    private static final Logger LOGGER = LogManager.getLogger(GestaoProdutosSmokeTester.class);
    private final ProdutoCRUDSmokeTester crudTester = new ProdutoCRUDSmokeTester();
    private final ProdutoRepository produtoRepository = PersistenceContext.repositories().produtos();

    @Override
    public boolean execute() {
        testProdutoCRUD();
        importarProdutosCSV();
        listarTodosProdutos();
        listarProdutosSemFichaProducao();
        return true;
    }

    private void testProdutoCRUD() {
        crudTester.testProdutoCRUD();
    }

    private void importarProdutosCSV() {
        final ImportarProdutosCSVController controller = new ImportarProdutosCSVController();
        
        int cont = controller.importarProdutosCSV(System.getProperty("user.dir")+"/ficheirosTeste/produtos.csv");
        
        LOGGER.info("--- IMPORTAR PRODUTOS CSV ---");
        LOGGER.info("Importados " + cont + " produtos.");
    }

    private void listarProdutosSemFichaProducao() {
        final Iterable<Produto> produtosSemFicha = produtoRepository.produtosSemFichaProducao();
        LOGGER.info("--- PRODUTOS SEM FICHA DE PRODUÇÃO ---");
        for (final Produto p : produtosSemFicha) {
            LOGGER.info("{}", p.identity());
        }
    }

    private void listarTodosProdutos() {
        final Iterable<Produto> produtos = produtoRepository.findAll();
        LOGGER.info("--- PRODUTOS ---");
        for (final Produto p : produtos) {
            LOGGER.info("{}", p.identity());
        }
    }
}
