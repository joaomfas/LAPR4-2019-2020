package eapli.base.gestaochaofabrica.export.application;

import eapli.base.gestaomateriasprimas.application.ListarMateriasPrimasService;
import eapli.base.gestaomateriasprimas.domain.MateriaPrima;
import eapli.base.utils.ManipularFicheiroXML;
import java.io.IOException;
import org.w3c.dom.Document;

/**
 *
 * @author mdias
 */
public class ExportMateriasPrimasController {
    private final ListarMateriasPrimasService listSvc = new ListarMateriasPrimasService();
    private final MateriaPrimaExporterFactory factory = new MateriaPrimaExporterFactory();
    private final MateriaPrimaExporterService exportSvc = new MateriaPrimaExporterService();

    public void export(String filename, FileFormat format) throws IOException {
        final ChaoFabricaExporter exporter = factory.build(format);

        final Iterable<MateriaPrima> materiasPrimas = listSvc.allMateriasPrimas();
        exportSvc.export(materiasPrimas, filename, exporter);
        ManipularFicheiroXML.exportarDocumento(filename);
    }    
}
