package eapli.base.gestaomensagens.application.importer;

import eapli.base.gestaomensagens.dominio.Mensagem;
import eapli.base.gestaoprodutos.fileImporters.ImportarProdutoService;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ImportarMensagemService {

    private static final Logger logger = LogManager.getLogger(ImportarProdutoService.class);

    
    public Iterable<Mensagem> importar(String path, IMensagemImporter importer)
            throws IOException {
        try {
            importer.open(path);
            Iterable<Mensagem> mensagens = importer.importFromFile();
            importer.close();
            return mensagens;
        } catch (final IOException e) {
            logger.error("Erro ao importar mensagens!", e);
            throw e;
        }
    }
}
