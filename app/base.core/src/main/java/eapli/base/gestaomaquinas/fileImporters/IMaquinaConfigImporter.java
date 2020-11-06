package eapli.base.gestaomaquinas.fileImporters;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IMaquinaConfigImporter {
    void open(String path) throws FileNotFoundException;
    byte[] importarFicheiro() throws IOException;    
    void close() throws IOException;
}
