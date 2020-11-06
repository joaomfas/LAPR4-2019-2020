package eapli.base.gestaochaofabrica.export.application;


/**
 *
 * @author mdias
 */
public class DepositoExporterFactory {
    public ChaoFabricaExporter build(FileFormat format) {
        switch (format) {
        case XML:
            return new XmlDepositoExporter();
        }
        throw new IllegalStateException("Unknown format");
    }
}
