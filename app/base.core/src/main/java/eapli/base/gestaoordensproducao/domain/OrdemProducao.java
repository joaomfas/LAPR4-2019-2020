package eapli.base.gestaoordensproducao.domain;

import eapli.base.gestaoprodutos.domain.Produto;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Entity
public class OrdemProducao implements AggregateRoot<CodigoOrdemProducao> {
    
    private static final long serialVersionUID = 1L;
    
    @Version
    private Long version;
    @EmbeddedId
    private CodigoOrdemProducao codigoOrdemProducao;
    @ManyToOne()
    private Produto produto;
    private Double quantidade;
    private DataEmissao dataEmissao;
    private DataInicio dataInicio;
    private DataPrevista dataPrevista;
    private DataFim dataFim;
    private EstadoOrdemProducao estado;
    @ElementCollection
    private Set<Encomenda> encomendas;
    
    public OrdemProducao(CodigoOrdemProducao numOrdemProducao, DataEmissao dataEmissao, Produto produto, Double quantidade,
            DataPrevista dataPrevista, Set<Encomenda> encomendas) {
        requisitosCriacaoOrdemProducao(numOrdemProducao, dataEmissao, dataPrevista, encomendas, produto, quantidade);
        this.codigoOrdemProducao = numOrdemProducao;
        this.produto = produto;
        this.quantidade = quantidade;
        this.dataEmissao = dataEmissao;
        this.dataPrevista = dataPrevista;
        this.dataFim = null;
        this.dataInicio = null;
        this.encomendas = new HashSet<>(encomendas);
        this.estado = EstadoOrdemProducao.PENDENTE;
    }
    
    protected OrdemProducao() {
        this.encomendas = null;
    }
    
    public void updateDataInicio(DataInicio dataInicio) {
        Preconditions.nonNull(dataInicio, "Data de inicio não pode ser null!");
        this.dataInicio = dataInicio;
        if (dataInicio.dataInicio().getTime().getTime() <= Calendar.getInstance().getTime().getTime()) {
            this.estado = EstadoOrdemProducao.EXECUCAO;
        }
    }
    
    public void updateDataFim(DataFim dataFim) {
        Preconditions.nonNull(dataFim, "Data fim não pode ser null!");
        this.dataFim = dataFim;
        if (dataInicio.dataInicio().getTime().getTime() >= Calendar.getInstance().getTime().getTime()) {
            this.estado = EstadoOrdemProducao.CONCLUIDA;
        }
    }
    
    public void updateEstadoOrdemProducao(EstadoOrdemProducao estado) {
        Preconditions.nonNull(estado, "Estado da ordem de produção pode ser null!");
        this.estado = estado;
    }
    
    private void requisitosCriacaoOrdemProducao(CodigoOrdemProducao codOrdemProducao,
            DataEmissao dataEmissao, DataPrevista dataPrevista, Set<Encomenda> encomendas, Produto produto, Double quantidade) {
        Preconditions.nonNull(codOrdemProducao, "Código de Ordem de Produção null!");
        Preconditions.nonNull(dataEmissao, "Data de Emissão null!");
        Preconditions.nonNull(dataPrevista, "Data Prevista null!");
        Preconditions.nonNull(encomendas, "Encomendas null!");
        Preconditions.ensure(encomendas.size() > 0, "Sem encomendas associadas!");
        Preconditions.nonNull(produto, "Produto null!");
        Preconditions.ensure(quantidade > 0, "Quantidade negativa!");
    }
    
    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof OrdemProducao)) {
            return false;
        }
        
        final OrdemProducao that = (OrdemProducao) other;
        if (this == that) {
            return true;
        }
        
        return identity().equals(that.identity());
    }

    public EstadoOrdemProducao estado() {
        return estado;
    }
    
    public Produto produto(){
        return produto;
    }
    
    public Double quantidade(){
        return quantidade;
    }
    
    public DataEmissao dataEmissao(){
        return dataEmissao;
    }
    
    public DataInicio dataInicio(){
        return dataInicio;
    }
    
    public DataPrevista dataPrevista(){
        return dataPrevista;
    }
    
    public DataFim dataFim(){
        return dataFim;
    }
    
    public Set<Encomenda> encomendas(){
        return encomendas;
    }
    
    @Override
    public CodigoOrdemProducao identity() {
        return this.codigoOrdemProducao;
    }
}
