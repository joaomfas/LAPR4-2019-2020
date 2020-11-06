package eapli.base.gestaochaofabrica.export.application;

/**
 *
 * @author mdias
 */
public class ExecucaoOrdemProducaoExporterFactory {
    public ChaoFabricaExporter build(FileFormat format) {
        switch (format) {
        case XML:
            return new XmlExecucaoOrdemProducaoExporter();
        }
        throw new IllegalStateException("Unknown format");
    }
}
