package eapli.base.gestaochaofabrica.export.application;

/**
 *
 * @author mdias
 */
public class MateriaPrimaExporterFactory {
    public ChaoFabricaExporter build(FileFormat format) {
        switch (format) {
        case XML:
            return new XmlMateriaPrimaExporter();
        }
        throw new IllegalStateException("Unknown format");
    }
}
