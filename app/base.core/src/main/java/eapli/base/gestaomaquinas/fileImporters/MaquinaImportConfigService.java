package eapli.base.gestaomaquinas.fileImporters;

import eapli.base.gestaoprodutos.fileImporters.ImportarProdutoService;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MaquinaImportConfigService {
    
    private static final Logger logger = LogManager.getLogger(ImportarProdutoService.class);
    
    public byte[] importar(String path) throws IOException {
        byte[] configFileInBytes;
        MaquinaConfigImporter importer = new MaquinaConfigImporter();        
        try {
            importer.open(path);
            configFileInBytes = importer.importarFicheiro();
            importer.close();
            return configFileInBytes;
        } catch (final IOException e) {
            logger.error("Erro ao importar ficheiro de configuração!", e);
            throw e;
        }
    }
    
}
