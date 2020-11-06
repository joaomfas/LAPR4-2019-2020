package eapli.base.gestaoordensproducao.fileImporters;

import eapli.base.gestaoordensproducao.domain.OrdemProducao;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author mdias
 */
public interface IOrdemProducaoImporter {
    void open(String path) throws FileNotFoundException;
    Iterable<OrdemProducao> importFromFile() throws IOException;
    void criarFicheiroErros(int numErros, List<String[]> erros, String path);
    void close() throws IOException;
}
