package eapli.base.gestaochaofabrica.export.application;

import eapli.base.gestaoordensproducao.domain.ExecucaoOrdemProducao;
import java.io.IOException;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author mdias
 */
public class ExecucaoOrdemProducaoExporterService {
    private static final Logger logger = LogManager.getLogger(ExecucaoOrdemProducaoExporterService.class);

    public void export(Iterable<ExecucaoOrdemProducao> execucoesOrdemProducao, String filename, 
            ChaoFabricaExporter exporter, Date dataI, Date dataF)
            throws IOException {
        try {
            exporter.begin(filename);

            boolean hasPrevious = false;
            for (final ExecucaoOrdemProducao eop : execucoesOrdemProducao) {
                if (hasPrevious) {
                    exporter.elementSeparator();
                }

                exporter.element(eop, dataI, dataF);
                hasPrevious = true;
            }

            exporter.end();
        } catch (final IOException e) {
            logger.error("Problem exporting execucoes de ordem de producao", e);
            throw e;
        } finally {
            exporter.cleanup();
        }
    }
}
