package eapli.base.gestaoprodutos.fileImporters;

import eapli.base.gestaoprodutos.domain.Produto;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ImportarProdutoService {

    private static final Logger logger = LogManager.getLogger(ImportarProdutoService.class);

    public Iterable<Produto> importar(String path, IProdutoImporter importer)
            throws IOException {
        try {
            importer.open(path);
            Iterable<Produto> produtos = importer.importFromFile();
            importer.close();
            return produtos;
        } catch (final IOException e) {
            logger.error("Erro ao importar produtos!", e);
            throw e;
        }
    }
}
