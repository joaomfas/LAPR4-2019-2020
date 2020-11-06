/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.gestaoprodutos.fileImporters;

import eapli.base.gestaoprodutos.domain.Produto;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author joaoferreira
 */
public interface IProdutoImporter {
    void open(String path) throws FileNotFoundException;
    Iterable<Produto> importFromFile() throws IOException;
    void criarFicheiroErros(int numErros, List<String[]> erros, String path);
    void close() throws IOException;
}
