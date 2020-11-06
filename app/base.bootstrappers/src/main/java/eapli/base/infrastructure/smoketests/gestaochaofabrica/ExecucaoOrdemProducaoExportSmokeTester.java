package eapli.base.infrastructure.smoketests.gestaochaofabrica;

import eapli.base.gestaochaofabrica.export.application.ExportExecucoesOrdemProducaoController;
import eapli.base.gestaochaofabrica.export.application.FileFormat;
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
public class ExecucaoOrdemProducaoExportSmokeTester implements Action{
    private static final Logger LOGGER = LogManager
            .getLogger(ExecucaoOrdemProducaoExportSmokeTester.class);

    final ExportExecucoesOrdemProducaoController exportExecucoesOrdemProducaoController = new ExportExecucoesOrdemProducaoController();

    @Override
    public boolean execute() {
        testExportTo(FileFormat.XML);
        return true;
    }

    private void testExportTo(FileFormat format) {
        final String filename = "xml/export-execucoesOrdemProducao." + format.toString();
        try {
            DateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
            Date dataI;
            Date dataF;
            dataI = formatDate.parse("01/01/2019");
            dataF = formatDate.parse("31/12/2019");
            exportExecucoesOrdemProducaoController.export(filename, format, dataI, dataF);
            outputExportedContent(format, filename);
        } catch (final Exception e) {
            LOGGER.error(e);
        }
    }

    private void outputExportedContent(FileFormat format, final String filename)
            throws IOException {
        Files.contentOf(new FileInputStream(filename));
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("-- EXPORT EXECUCOES ORDEM PRODUCAO TO {} --", format);

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
