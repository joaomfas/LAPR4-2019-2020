package eapli.base.gestaochaofabrica.export.application;

import eapli.base.gestaomateriasprimas.domain.MateriaPrima;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 *
 * @author mdias
 */
public class MateriaPrimaExporterService {
    private static final Logger logger = LogManager.getLogger(MateriaPrimaExporterService.class);

    public void export(Iterable<MateriaPrima> materiasPrimas, String filename, ChaoFabricaExporter exporter)
            throws IOException {
        try {
            exporter.begin(filename);

            boolean hasPrevious = false;
            for (final MateriaPrima mp : materiasPrimas) {
                if (hasPrevious) {
                    exporter.elementSeparator();
                }

                exporter.element(mp, null, null);
                hasPrevious = true;
            }

            exporter.end();
        } catch (final IOException e) {
            logger.error("Problem exporting materias-primas", e);
            throw e;
        } finally {
            exporter.cleanup();
        }
    }
}
