package eapli.base.gestaoprodutos.fileImporters;

import eapli.base.Application;
import eapli.base.gestaoprodutos.domain.Produto;
import eapli.base.gestaoprodutos.domain.ProdutoBuilder;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CSVProdutoImporter implements IProdutoImporter {

    private BufferedReader leitor;
    private ProdutoBuilder builder = new ProdutoBuilder();

    @Override
    public void open(String path) throws FileNotFoundException {
        leitor = new BufferedReader(new FileReader(path));
    }

    @Override
    public Iterable<Produto> importFromFile() throws IOException {
        int numLinha = 1;
        int numErros = 0;
        List<String[]> erros = new ArrayList<>();
        erros.add(new String[]{"CódigoFabrico", "CódigoComercial", "Descrição Breve", "Descrição Completa", "Unidade", "Categoria"});
        String sepparator = Application.settings().getCSVSeparator();
        List<Produto> prods = new ArrayList<>();
        boolean cabecalho = true;
        String linha;
        String[] info;

        linha = leitor.readLine();

        while (linha != null) {
            if (!cabecalho) {
                info = linha.split(sepparator);

                if (info.length != 6) {
                    System.out.println("Erro na Linha " + numLinha + ": Faltam parâmetros.");
                    numErros++;
                    erros.add(info);
                } else {

                    final String codigoFabrico = info[0].trim();
                    final String codigoComercial = info[1].trim();
                    final String descBreve = info[2].trim();
                    final String descCompleta = info[3].trim();
                    final String unidadeMedida = info[4].trim();
                    final String catProduto = info[5].trim();

                    try {
                        prods.add(builder.comCodigoFabrico(codigoFabrico).comCodigoComercial(codigoComercial).comDescricaoBreve(descBreve)
                                .comDescricaoCompleta(descCompleta).comCategoria(catProduto).comUnidadeMedida(unidadeMedida).build());
                    } catch (Exception e) {
                        System.out.println("Erro na Linha " + numLinha + ": " + e.getMessage());
                        numErros++;
                        erros.add(info);
                    }
                }
            }
            linha = leitor.readLine();
            numLinha++;
            cabecalho = false;
        }

        if (numErros > 0) {
            String pathErros = System.getProperty("user.home") + "/produtos_erros_importacao.csv";
            criarFicheiroErros(numErros, erros, pathErros);
        }

        return prods;
    }

    @Override
    public void close() throws IOException {
        leitor.close();
    }

    @Override
    public void criarFicheiroErros(int numErros, List<String[]> erros, String path) {
        String sepparator = Application.settings().getCSVSeparator();
        try {
            FileWriter csvWriter = new FileWriter(path);
            for (String[] linha : erros) {
                int i;
                for (i = 0; i < linha.length; i++) {
                    csvWriter.append(linha[i]);
                    if(i != linha.length-1){
                        csvWriter.append(sepparator);
                    }
                }
                csvWriter.append("\n");
            }
            csvWriter.flush();
            csvWriter.close();
            System.out.println("Ficheiro de erros criado em: " + path);
        } catch (IOException ex) {
            Logger.getLogger(CSVProdutoImporter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
