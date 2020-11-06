package eapli.base.gestaochaofabrica.export.application;

import eapli.base.gestaolinhasproducao.application.ListarLinhasProducaoService;
import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import eapli.base.utils.ManipularFicheiroXML;
import java.io.IOException;

/**
 *
 * @author mdias
 */
public class ExportLinhasProducaoController {
    private final ListarLinhasProducaoService listSvc = new ListarLinhasProducaoService();
    private final LinhaProducaoExporterFactory factory = new LinhaProducaoExporterFactory();
    private final LinhaProducaoExporterService exportSvc = new LinhaProducaoExporterService();

    public void export(String filename, FileFormat format) throws IOException {
        final ChaoFabricaExporter exporter = factory.build(format);

        final Iterable<LinhaProducao> linhasProducao = listSvc.allLinhasProducao();
        exportSvc.export(linhasProducao, filename, exporter);
        ManipularFicheiroXML.exportarDocumento(filename);
    }

}
