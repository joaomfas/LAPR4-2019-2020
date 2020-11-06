package eapli.base.gestaoordensproducao.fileImporters;

import eapli.base.Application;
import eapli.base.gestaoordensproducao.domain.CodigoOrdemProducao;
import eapli.base.gestaoordensproducao.domain.DataEmissao;
import eapli.base.gestaoordensproducao.domain.DataPrevista;
import eapli.base.gestaoordensproducao.domain.Encomenda;

import eapli.base.gestaoordensproducao.domain.OrdemProducao;
import eapli.base.gestaoordensproducao.domain.OrdemProducaoBuilder;
import eapli.base.gestaoprodutos.application.ListarProdutosService;
import eapli.base.gestaoprodutos.domain.CodigoFabrico;
import eapli.base.gestaoprodutos.domain.Produto;
import eapli.base.gestaoprodutos.repositories.ProdutoRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mdias
 */
public class CSVOrdemProducaoImporter implements IOrdemProducaoImporter {

    private BufferedReader leitor;
    private final ListarProdutosService listarProdutos = new ListarProdutosService();
    private final ProdutoRepository produtoRepo = PersistenceContext.repositories().produtos();

    @Override
    public void open(String path) throws FileNotFoundException {
        leitor = new BufferedReader(new FileReader(path));
    }

    @Override
    public Iterable<OrdemProducao> importFromFile() throws IOException {
        int numLinha = 1;
        int numErros = 0;
        List<String[]> erros = new ArrayList<>();
        erros.add(new String[]{"Código Ordem Producao", "Data Emissao", "Data Prevista", "Produto", "Quantidade", "Lista Encomendas"});
        String separator = Application.settings().getCSVSeparator();
        List<OrdemProducao> ords = new ArrayList<>();
        boolean cabecalho = true;
        String linha;
        String[] info;

        linha = leitor.readLine();

        while (linha != null) {
            if (!cabecalho) {
                info = linha.split(separator);

                if (info.length != 6) {
                    System.out.println("Erro na Linha " + numLinha + ": Faltam parâmetros.");
                    numErros++;
                    erros.add(info);
                } else {

                    final String numOrdemProducao = info[0].trim();
                    final String dataEmissao = info[1].trim();
                    final String dataPrevista = info[2].trim();
                    final String produto = info[3].trim();
                    final String quantidade = info[4].trim();
                    final String listaEncomendas = info[5].trim();

                    Produto p = produtoRepo.ofIdentity(new CodigoFabrico(produto)).orElse(null);

                    Set<Encomenda> listaEncs = new HashSet<>();
                    listaEncs.add(new Encomenda(listaEncomendas));

                    try {

                        ords.add(new OrdemProducao(new CodigoOrdemProducao(numOrdemProducao), new DataEmissao(dataEmissao), p, Double.parseDouble(quantidade), new DataPrevista(dataPrevista), listaEncs));

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
            String pathErros = System.getProperty("user.home") + "/ordensProducao_erros_importacao.csv";
            criarFicheiroErros(numErros, erros, pathErros);
        }

        return ords;
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
                    if (i != linha.length - 1) {
                        csvWriter.append(sepparator);
                    }
                }
                csvWriter.append("\n");
            }
            csvWriter.flush();
            csvWriter.close();
            System.out.println("Ficheiro de erros criado em: " + path);
        } catch (IOException ex) {
            Logger.getLogger(CSVOrdemProducaoImporter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
