package eapli.base.gestaochaofabrica.export.application;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eapli.base.gestaomaquinas.domain.Maquina;

/**
 *
 * @author mdias
 */
public class MaquinaExporterService {
    private static final Logger logger = LogManager.getLogger(DepositoExporterService.class);

    public void export(Iterable<Maquina> maquinas, String filename, ChaoFabricaExporter exporter)
            throws IOException {
        try {
            exporter.begin(filename);

            boolean hasPrevious = false;
            for (final Maquina m : maquinas) {
                if (hasPrevious) {
                    exporter.elementSeparator();
                }

                exporter.element(m, null, null);
                hasPrevious = true;
            }

            exporter.end();
        } catch (final IOException e) {
            logger.error("Problem exporting maquinas", e);
            throw e;
        } finally {
            exporter.cleanup();
        }
    }
}
