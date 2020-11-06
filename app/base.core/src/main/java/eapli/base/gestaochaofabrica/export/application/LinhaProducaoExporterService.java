package eapli.base.gestaochaofabrica.export.application;

import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mdias
 */
public class LinhaProducaoExporterService {

    private static final Logger logger = LogManager.getLogger(LinhaProducaoExporterService.class);

    public void export(Iterable<LinhaProducao> linhasProducao, String filename, ChaoFabricaExporter exporter)
            throws IOException {
        try {
            exporter.begin(filename);

            boolean hasPrevious = false;
            for (final LinhaProducao lp : linhasProducao) {
                if (hasPrevious) {
                    exporter.elementSeparator();
                }

                exporter.element(lp, null, null);
                hasPrevious = true;
            }

            exporter.end();
        } catch (final IOException e) {
            logger.error("Problem exporting linhas de producao", e);
            throw e;
        } finally {
            exporter.cleanup();
        }
    }
}
