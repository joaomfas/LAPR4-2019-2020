package eapli.base.gestaoprodutos.application;

import eapli.base.gestaoprodutos.domain.Produto;
import eapli.base.gestaoprodutos.fileImporters.IProdutoImporter;
import eapli.base.gestaoprodutos.fileImporters.ImportarProdutoFactory;
import eapli.base.gestaoprodutos.fileImporters.ImportarProdutoService;
import eapli.base.gestaoprodutos.fileImporters.TipoFicheiroProdutoImporter;
import eapli.base.gestaoprodutos.repositories.ProdutoRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.Controller;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.util.Console;

public class ImportarProdutosCSVController implements Controller {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ProdutoRepository repository = PersistenceContext.repositories().produtos();
    private final ImportarProdutoFactory factory = new ImportarProdutoFactory();
    private final ImportarProdutoService importCsv = new ImportarProdutoService();

    public int importarProdutosCSV(final String path) {
        int contador = 0;
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.GESTOR_PRODUCAO, BaseRoles.POWER_USER);

        try {
            final IProdutoImporter exporter = factory.build(TipoFicheiroProdutoImporter.CSV);
            Iterable<Produto> prods = importCsv.importar(path, exporter);

            for (Produto prod : prods) {

                if (repository.containsOfIdentity(prod.identity())) {
                    final String msgConf = Console.readLine("O produto com o código de fabrico "
                            + prod.identity().toString() + " já se encontra registado. Pretende modificá-lo? S/N");
                    if (msgConf.equalsIgnoreCase("s")) {
                        repository.deleteOfIdentity(prod.identity());
                        repository.save(prod);
                        contador++;
                    }
                } else {
                    repository.save(prod);
                    contador++;
                }

            }
            return contador;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
