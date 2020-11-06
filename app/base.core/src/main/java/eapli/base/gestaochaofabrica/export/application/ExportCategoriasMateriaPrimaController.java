package eapli.base.gestaochaofabrica.export.application;

import eapli.base.gestaomateriasprimas.application.ListarCategoriaMateriaPrimaService;
import eapli.base.gestaomateriasprimas.domain.CategoriaMateriaPrima;
import eapli.base.utils.ManipularFicheiroXML;
import java.io.IOException;

/**
 *
 * @author mdias
 */
public class ExportCategoriasMateriaPrimaController {
    private final ListarCategoriaMateriaPrimaService listSvc = new ListarCategoriaMateriaPrimaService();
    private final CategoriaMateriaPrimaExporterFactory factory = new CategoriaMateriaPrimaExporterFactory();
    private final CategoriaMateriaPrimaExporterService exportSvc = new CategoriaMateriaPrimaExporterService();

    public void export(String filename, FileFormat format) throws IOException {
        final ChaoFabricaExporter exporter = factory.build(format);

        final Iterable<CategoriaMateriaPrima> categorias = listSvc.allCategoriasMateriasPrimas();
        exportSvc.export(categorias, filename, exporter);
        ManipularFicheiroXML.exportarDocumento(filename);
    }

    
    
}
