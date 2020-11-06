package eapli.base.gestaochaofabrica.export.application;

/**
 *
 * @author mdias
 */
public class ProdutoExporterFactory {
    public ChaoFabricaExporter build(FileFormat format) {
        switch (format) {
        case XML:
            return new XmlProdutoExporter();
        }
        throw new IllegalStateException("Unknown format");
    }
}
