package eapli.base.infrastructure.smoketests.gestaochaofabrica;

import eapli.base.gestaochaofabrica.export.application.ExportProdutosController;
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
public class ProdutoExportSmokeTester implements Action{
    private static final Logger LOGGER = LogManager
            .getLogger(ProdutoExportSmokeTester.class);

    final ExportProdutosController exportProdutosController = new ExportProdutosController();

    @Override
    public boolean execute() {
        testExportTo(FileFormat.XML);
        return true;
    }

    private void testExportTo(FileFormat format) {
        final String filename = "xml/export-produtos." + format.toString();
        try {
            exportProdutosController.export(filename, format);
            outputExportedContent(format, filename);
        } catch (final IOException e) {
            LOGGER.error(e);
        }
    }

    private void outputExportedContent(FileFormat format, final String filename)
            throws IOException {
        Files.contentOf(new FileInputStream(filename));
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("-- EXPORT PRODUTOS TO {} --", format);

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
