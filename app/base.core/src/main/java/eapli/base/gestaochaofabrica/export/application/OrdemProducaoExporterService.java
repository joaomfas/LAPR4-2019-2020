package eapli.base.gestaochaofabrica.export.application;

import eapli.base.gestaoordensproducao.domain.OrdemProducao;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mdias
 */
public class OrdemProducaoExporterService {
    private static final Logger logger = LogManager.getLogger(OrdemProducaoExporterService.class);

    public void export(Iterable<OrdemProducao> ordensProducao, String filename, ChaoFabricaExporter exporter)
            throws IOException {
        try {
            exporter.begin(filename);

            boolean hasPrevious = false;
            for (final OrdemProducao op : ordensProducao) {
                if (hasPrevious) {
                    exporter.elementSeparator();
                }

                exporter.element(op, null, null);
                hasPrevious = true;
            }

            exporter.end();
        } catch (final IOException e) {
            logger.error("Problem exporting ordens de producao", e);
            throw e;
        } finally {
            exporter.cleanup();
        }
    }
}
