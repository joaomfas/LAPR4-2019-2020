/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.gestaomensagens.application.importer;

import eapli.base.gestaomensagens.dominio.Mensagem;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author joaoferreira
 */
public interface IMensagemImporter {
    void open(String path) throws FileNotFoundException;
    Iterable<Mensagem> importFromFile() throws IOException;
    void close() throws IOException;
}
