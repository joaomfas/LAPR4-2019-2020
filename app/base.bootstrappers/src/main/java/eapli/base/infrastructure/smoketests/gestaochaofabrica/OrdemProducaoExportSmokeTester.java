package eapli.base.infrastructure.smoketests.gestaochaofabrica;

import eapli.base.gestaochaofabrica.export.application.ExportOrdensProducaoController;
import eapli.base.gestaochaofabrica.export.application.FileFormat;
import eapli.framework.actions.Action;
import eapli.framework.util.Files;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mdias
 */
public class OrdemProducaoExportSmokeTester implements Action{
    private static final Logger LOGGER = LogManager
            .getLogger(OrdemProducaoExportSmokeTester.class);

    final ExportOrdensProducaoController exportOrdensProducaoController = new ExportOrdensProducaoController();

    @Override
    public boolean execute() {
        testExportTo(FileFormat.XML);
        return true;
    }

    private void testExportTo(FileFormat format) {
        final String filename = "xml/export-ordensProducao." + format.toString();
        try {
            exportOrdensProducaoController.export(filename, format);
            outputExportedContent(format, filename);
        } catch (final IOException e) {
            LOGGER.error(e);
        }
    }

    private void outputExportedContent(FileFormat format, final String filename)
            throws IOException {
        Files.contentOf(new FileInputStream(filename));
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("-- EXPORT ORDENS PRODUCAO TO {} --", format);

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(filename), "UTF-8"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    LOGGER.info(line);
                }
            }

            LOGGER.info("----");
        }
    }
}
