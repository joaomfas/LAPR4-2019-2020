package eapli.base.gestaochaofabrica.export.application;

import eapli.base.gestaomaquinas.application.ListarMaquinasService;
import eapli.base.gestaomaquinas.domain.Maquina;
import eapli.base.utils.ManipularFicheiroXML;
import java.io.IOException;
import org.w3c.dom.Document;

/**
 *
 * @author mdias
 */
public class ExportMaquinasController {
    private final ListarMaquinasService listSvc = new ListarMaquinasService();
    private final MaquinaExporterFactory factory = new MaquinaExporterFactory();
    private final MaquinaExporterService exportSvc = new MaquinaExporterService();

    public void export(String filename, FileFormat format) throws IOException {
        final ChaoFabricaExporter exporter = factory.build(format);

        final Iterable<Maquina> maquinas = listSvc.todasMaquinas();
        exportSvc.export(maquinas, filename, exporter);
        ManipularFicheiroXML.exportarDocumento(filename);
    }

}
