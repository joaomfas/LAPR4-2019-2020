package eapli.base.gestaochaofabrica.export.application;

/**
 *
 * @author mdias
 */
public class OrdemProducaoExporterFactory {
    public ChaoFabricaExporter build(FileFormat format) {
        switch (format) {
        case XML:
            return new XmlOrdemProducaoExporter();
        }
        throw new IllegalStateException("Unknown format");
    }
}
