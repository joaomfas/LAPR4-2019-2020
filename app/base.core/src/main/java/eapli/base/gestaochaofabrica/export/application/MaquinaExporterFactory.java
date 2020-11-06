package eapli.base.gestaochaofabrica.export.application;

/**
 *
 * @author mdias
 */
public class MaquinaExporterFactory {
    public ChaoFabricaExporter build(FileFormat format) {
        switch (format) {
        case XML:
            return new XmlMaquinaExporter();
        }
        throw new IllegalStateException("Unknown format");
    }
}
