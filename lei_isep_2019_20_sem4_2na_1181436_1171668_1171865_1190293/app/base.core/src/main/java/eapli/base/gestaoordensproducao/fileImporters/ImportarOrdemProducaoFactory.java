package eapli.base.gestaoordensproducao.fileImporters;

/**
 *
 * @author mdias
 */
public class ImportarOrdemProducaoFactory {
    public final IOrdemProducaoImporter build(FileFormat formato){
        
        switch (formato) {
        case CSV:
            return new CSVOrdemProducaoImporter();
        }
        throw new IllegalStateException("Formato desconhecido!");
    }
}
