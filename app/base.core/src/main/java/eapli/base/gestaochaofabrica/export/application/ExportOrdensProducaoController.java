package eapli.base.gestaochaofabrica.export.application;

import eapli.base.gestaoordensproducao.application.ListarOrdensProducaoService;
import eapli.base.gestaoordensproducao.domain.OrdemProducao;
import eapli.base.utils.ManipularFicheiroXML;
import java.io.IOException;
/**
 *
 * @author mdias
 */
public class ExportOrdensProducaoController {
    private final ListarOrdensProducaoService listSvc = new ListarOrdensProducaoService();
    private final OrdemProducaoExporterFactory factory = new OrdemProducaoExporterFactory();
    private final OrdemProducaoExporterService exportSvc = new OrdemProducaoExporterService();

    public void export(String filename, FileFormat format) throws IOException {
        final ChaoFabricaExporter exporter = factory.build(format);

        final Iterable<OrdemProducao> ordensProducao = listSvc.allOrdensProducao();
        exportSvc.export(ordensProducao, filename, exporter);
        ManipularFicheiroXML.exportarDocumento(filename);
    }
}
