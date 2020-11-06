package eapli.base.gestaochaofabrica.export.application;

import eapli.base.gestaodepositos.application.ListarDepositosService;
import eapli.base.gestaodepositos.domain.Deposito;
import eapli.base.utils.ManipularFicheiroXML;
import java.io.IOException;

/**
 *
 * @author mdias
 */
public class ExportDepositosController {
    private final ListarDepositosService listSvc = new ListarDepositosService();
    private final DepositoExporterFactory factory = new DepositoExporterFactory();
    private final DepositoExporterService exportSvc = new DepositoExporterService();

    public void export(String filename, FileFormat format) throws IOException {
        final ChaoFabricaExporter exporter = factory.build(format);

        final Iterable<Deposito> depositos = listSvc.allDepositos();
        exportSvc.export(depositos, filename, exporter);
        ManipularFicheiroXML.exportarDocumento(filename);
    }
}
