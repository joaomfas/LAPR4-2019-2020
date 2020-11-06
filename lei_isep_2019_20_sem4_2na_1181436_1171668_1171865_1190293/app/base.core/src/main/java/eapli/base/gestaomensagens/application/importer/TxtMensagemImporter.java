package eapli.base.gestaomensagens.application.importer;

import eapli.base.gestaomensagens.dominio.Mensagem;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TxtMensagemImporter implements IMensagemImporter {

    private BufferedReader leitor;

    @Override
    public void open(String path) throws FileNotFoundException {
        leitor = new BufferedReader(new FileReader(path));
    }

    @Override
    public Iterable<Mensagem> importFromFile() throws IOException {
        List<Mensagem> msgs = new ArrayList<>();
        String linha;

        linha = leitor.readLine();

        while (linha != null) {
            Date dataRecepcao = new Date();
            String[] params = linha.split(";");
            String timestamp = params[2];
            int ano = Integer.parseInt(timestamp.substring(0, 4)) - 1900;
            int mes = Integer.parseInt(timestamp.substring(4, 6));
            int dia = Integer.parseInt(timestamp.substring(6, 8));
            int hora = Integer.parseInt(timestamp.substring(8, 10));
            int min = Integer.parseInt(timestamp.substring(10, 12));
            int seg = Integer.parseInt(timestamp.substring(12, 14));
            Date dataGeracao = new Date(ano, mes, dia, hora, min, seg);
            msgs.add(new Mensagem(linha, dataGeracao, dataRecepcao));
            linha = leitor.readLine();
        }

        return msgs;
    }

    @Override
    public void close() throws IOException {
        leitor.close();
    }

}
