package eapli.base.gestaomaquinas.fileImporters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MaquinaConfigImporter implements IMaquinaConfigImporter {

    private File configFile;
    private byte[] configFileInBytes;
    FileInputStream fis;
    
    @Override
    public void open(String path) throws FileNotFoundException {
        configFile = new File(path);
        configFileInBytes = new byte[(int) configFile.length()];
    }

    @Override
    public byte[] importarFicheiro() throws IOException {
        configFileInBytes = new byte[(int) configFile.length()];
        fis = new FileInputStream(configFile);
        fis.read(configFileInBytes);
        return configFileInBytes;
    }

    @Override
    public void close() throws IOException {
        fis.close();
    }
    
}
