package eapli.base.gestaochaofabrica.export.application;

import eapli.base.gestaoprodutos.domain.Produto;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 *
 * @author mdias
 */
public class ProdutoExporterService {
    private static final Logger logger = LogManager.getLogger(ProdutoExporterService.class);

    public void export(Iterable<Produto> produtos, String filename, ChaoFabricaExporter exporter)
            throws IOException {
        try {
            exporter.begin(filename);

            boolean hasPrevious = false;
            for (final Produto p : produtos) {
                if (hasPrevious) {
                    exporter.elementSeparator();
                }

                exporter.element(p, null, null);
                hasPrevious = true;
            }

            exporter.end();
        } catch (final IOException e) {
            logger.error("Problem exporting produtos", e);
            throw e;
        } finally {
            exporter.cleanup();
        }
    }
}
