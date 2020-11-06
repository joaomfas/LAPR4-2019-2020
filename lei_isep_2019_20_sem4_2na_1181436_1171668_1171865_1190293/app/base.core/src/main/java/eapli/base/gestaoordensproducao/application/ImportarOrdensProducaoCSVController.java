package eapli.base.gestaoordensproducao.application;

import eapli.base.gestaoordensproducao.domain.OrdemProducao;
import eapli.base.gestaoordensproducao.fileImporters.FileFormat;
import eapli.base.gestaoordensproducao.fileImporters.IOrdemProducaoImporter;
import eapli.base.gestaoordensproducao.fileImporters.ImportarOrdemProducaoFactory;
import eapli.base.gestaoordensproducao.fileImporters.ImportarOrdemProducaoService;
import eapli.base.gestaoordensproducao.repositories.OrdemProducaoRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.Controller;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.util.Console;

/**
 *
 * @author mdias
 */
public class ImportarOrdensProducaoCSVController implements Controller{
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final OrdemProducaoRepository repository = PersistenceContext.repositories().ordensProducao();
    private final ImportarOrdemProducaoFactory factory = new ImportarOrdemProducaoFactory();
    private final ImportarOrdemProducaoService importCsv = new ImportarOrdemProducaoService();

    public int importarOrdensProducaoCSV(final String path) {
        int contador = 0;
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.GESTOR_PRODUCAO, BaseRoles.POWER_USER);

        try {
            final IOrdemProducaoImporter importer = factory.build(FileFormat.CSV);
            Iterable<OrdemProducao> ords = importCsv.importar(path, importer);

            for (OrdemProducao ord : ords) {

                if (repository.containsOfIdentity(ord.identity())) {
                    final String msgConf = Console.readLine("A ordem de producao com o codigo "
                            + ord.identity().toString() + " já se encontra registada. Pretende modificá-la? S/N");
                    if (msgConf.equalsIgnoreCase("s")) {
                        repository.deleteOfIdentity(ord.identity());
                        repository.save(ord);
                        contador++;
                    }
                } else {
                    repository.save(ord);
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
