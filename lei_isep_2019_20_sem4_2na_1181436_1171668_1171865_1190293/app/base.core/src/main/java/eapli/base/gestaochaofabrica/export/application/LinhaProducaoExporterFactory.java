package eapli.base.gestaochaofabrica.export.application;

/**
 *
 * @author mdias
 */
public class LinhaProducaoExporterFactory {
    public ChaoFabricaExporter build(FileFormat format) {
        switch (format) {
        case XML:
            return new XmlLinhaProducaoExporter();
        }
        throw new IllegalStateException("Unknown format");
    }
}
