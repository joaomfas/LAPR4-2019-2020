package eapli.base.gestaoordensproducao.domain;

import eapli.base.gestaoprodutos.domain.Produto;
import java.util.HashSet;
import java.util.Set;

public class OrdemProducaoBuilder {

    private Set<Encomenda> listaEncomendas = new HashSet<>();
    private CodigoOrdemProducao numOrdemProducao;
    private DataEmissao dataEmissao;
    private DataPrevista dataPrevista;
    private Produto produto;
    private Double quantidade;
    
    public void ordemProducaoDetails(String numOrdemProducao, String dataEmissao, String dataPrevista,
            Produto produto, Double quantidade) {
        this.numOrdemProducao = new CodigoOrdemProducao(numOrdemProducao);
        this.dataEmissao = new DataEmissao(dataEmissao);
        this.dataPrevista = new DataPrevista(dataPrevista);
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public void adicionarEncomenda(String codEncomenda) {
        Encomenda encomenda = new Encomenda(codEncomenda);
        listaEncomendas.add(encomenda);
    }

    public OrdemProducao build() {
       return new OrdemProducao(numOrdemProducao, dataEmissao, produto, quantidade, dataPrevista, listaEncomendas);
    }
}
