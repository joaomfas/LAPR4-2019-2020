package eapli.base.gestaochaofabrica.export.application;

import eapli.base.gestaoprodutos.application.ListarProdutosService;
import eapli.base.gestaoprodutos.domain.Produto;
import eapli.base.utils.ManipularFicheiroXML;
import java.io.IOException;

/**
 *
 * @author mdias
 */
public class ExportProdutosController {

    private final ListarProdutosService listSvc = new ListarProdutosService();
    private final ProdutoExporterFactory factory = new ProdutoExporterFactory();
    private final ProdutoExporterService exportSvc = new ProdutoExporterService();

    public void export(String filename, FileFormat format) throws IOException {
        final ChaoFabricaExporter exporter = factory.build(format);

        final Iterable<Produto> produtos = listSvc.allProdutos();
        exportSvc.export(produtos, filename, exporter);
        ManipularFicheiroXML.exportarDocumento(filename);
    }

}
