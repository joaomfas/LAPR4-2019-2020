package eapli.base.infrastructure.smoketests.gestaochaofabrica;

import eapli.base.gestaochaofabrica.export.application.ExportChaoFabricaController;
import eapli.base.gestaochaofabrica.export.application.FileFormat;
import eapli.base.gestaochaofabrica.export.application.InfoAExportar;
import eapli.base.gestaochaofabrica.export.application.OpcConversaoInfo;
import eapli.framework.actions.Action;
import eapli.framework.util.Files;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mdias
 */
public class ChaoFabricaExportSmokeTester implements Action {

    private static final Logger LOGGER = LogManager
            .getLogger(ChaoFabricaExportSmokeTester.class);

    final ExportChaoFabricaController exportChaoFabricaController = new ExportChaoFabricaController();

    /**
     *
     * @return
     */
    @Override
    public boolean execute() {
        testExportTo(FileFormat.XML);
        return true;
    }

    private void testExportTo(FileFormat format) {
        final String filename = "xml/ChaoDeFabrica." + format.toString();
        try {
            DateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
            Date dataI;
            Date dataF;
            dataI = formatDate.parse("01/01/2019");
            dataF = formatDate.parse("31/12/2019");
            exportChaoFabricaController.export(filename, InfoAExportar.TUDO, dataI, dataF, OpcConversaoInfo.EXPORTAR_XML);
            outputExportedContent(format, filename);
        } catch (final Exception e) {
            LOGGER.error(e);
        }
    }

    private void outputExportedContent(FileFormat format, final String filename)
            throws IOException {
        Files.contentOf(new FileInputStream(filename));
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("-- EXPORT CHAO DE FABRICA TO {} --", format);

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
