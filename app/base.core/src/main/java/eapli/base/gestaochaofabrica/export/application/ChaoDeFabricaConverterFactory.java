package eapli.base.gestaochaofabrica.export.application;

/**
 *
 * @author mdias
 */
public class ChaoDeFabricaConverterFactory {
    public ChaoFabricaConverter build(FileFormat format) {
        switch (format) {
            case HTML:
            case JSON:
            case TXT:
                return new XMLConverterService();
        }
        throw new IllegalStateException("Unknown format");
    }
}
