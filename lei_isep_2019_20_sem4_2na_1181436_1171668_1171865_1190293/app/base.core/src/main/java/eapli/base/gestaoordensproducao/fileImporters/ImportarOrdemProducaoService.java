package eapli.base.gestaoordensproducao.fileImporters;

import eapli.base.gestaoordensproducao.domain.OrdemProducao;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mdias
 */
public class ImportarOrdemProducaoService {
    private static final Logger logger = LogManager.getLogger(ImportarOrdemProducaoService.class);

    public Iterable<OrdemProducao> importar(String path, IOrdemProducaoImporter importer)
            throws IOException {
        try {
            importer.open(path);
            Iterable<OrdemProducao> ordensProducao = importer.importFromFile();
            importer.close();
            return ordensProducao;
        } catch (final IOException e) {
            logger.error("Erro ao importar ordens de producao!", e);
            throw e;
        }
    }
}
