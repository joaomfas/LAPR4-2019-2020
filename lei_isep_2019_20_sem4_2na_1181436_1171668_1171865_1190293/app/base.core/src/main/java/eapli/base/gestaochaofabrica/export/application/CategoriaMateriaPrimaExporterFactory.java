package eapli.base.gestaochaofabrica.export.application;

/**
 *
 * @author mdias
 */
public class CategoriaMateriaPrimaExporterFactory {
    public ChaoFabricaExporter build(FileFormat format) {
        switch (format) {
        case XML:
            return new XmlCategoriaMateriaPrimaExporter();
        }
        throw new IllegalStateException("Unknown format");
    }
}
