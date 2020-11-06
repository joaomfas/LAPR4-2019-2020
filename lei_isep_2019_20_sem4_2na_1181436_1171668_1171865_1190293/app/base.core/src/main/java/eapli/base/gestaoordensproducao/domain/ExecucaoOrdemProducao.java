package eapli.base.gestaoordensproducao.domain;

import eapli.base.gestaodepositos.domain.Deposito;
import eapli.base.gestaomaquinas.domain.Maquina;
import eapli.base.gestaoprodutos.domain.Componente;
import eapli.base.gestaoprodutos.domain.Produto;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;
import java.time.Duration;
import java.util.*;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity
public class ExecucaoOrdemProducao implements AggregateRoot<Long>{

    @Version
    private Long version;
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private static final long serialVersionUID = 1L;
    
    @OneToOne
    private final OrdemProducao ordemProducao;   
    @ElementCollection
    private final Set<Producao> producoes;
    @ElementCollection
    private final Set<Consumo> consumos;
    @ElementCollection
    private final Set<EtapaProducao> etapas;
    @ElementCollection
    private final Set<Estorno> estornos;
    @ElementCollection
    private List<Lote> lotes;
    
    protected ExecucaoOrdemProducao() {
        this.ordemProducao = null;
        this.consumos = null;
        this.producoes = null;
        this.etapas = null;
        this.estornos = null;
        this.lotes = null;
    }

    public ExecucaoOrdemProducao(OrdemProducao ordemProducao) {
        this.ordemProducao = ordemProducao;
        producoes = new HashSet<>();
        consumos = new HashSet<>();
        etapas = new HashSet<>();
        estornos = new HashSet<>();
        lotes = new ArrayList<>();
    }
    
    public Duration tempoBrutoTotal(){
        Duration tempoBrutoTotal = Duration.ZERO;
        
        etapas.forEach((etapa) -> {
            tempoBrutoTotal.plus(etapa.tempoBruto());
        });
        
        return tempoBrutoTotal;
    }
    
    public Duration tempoEfetivoTotal(){
        Duration tempoEfetivoTotal = Duration.ZERO;
        
        etapas.forEach((etapa) -> {
            tempoEfetivoTotal.plus(etapa.tempoEfetivo());
        });
        
        return tempoEfetivoTotal;
    }
    
    public boolean registarEtapa(EtapaProducao etapa){
        Preconditions.nonNull(etapa, "Etapa não pode ser null!");
        return etapas.add(etapa);
    }
    
    public boolean registarInicioAtividade(Maquina maquina, Calendar dataRegisto) {
        EtapaProducao etapa = new EtapaProducao(maquina);
        etapa.registarInicioAtividade(dataRegisto.getTimeInMillis());
        return registarEtapa(etapa);
    }
    
    public boolean registarFimAtividade(Maquina maquina, Calendar dataRegisto) {
        etapa(maquina).registarFimAtividade(dataRegisto.getTimeInMillis());
        return true;
    }
    
    public boolean registarParagemAtividade(Maquina maquina, Calendar dataRegisto) {
        etapa(maquina).registarParagem(dataRegisto.getTimeInMillis());
        return true;
    }
    
    public boolean registarRetomaAtividade(Maquina maquina, Calendar dataRegisto) {
        etapa(maquina).registarRetoma(dataRegisto.getTimeInMillis());
        return true;
    }
    
    public boolean atividadeConcluida() {
        for(EtapaProducao etapa: etapas) {
            if(!etapa.concluido()) 
                return false;
        }
        return true;
    }
    
    private EtapaProducao etapa(Maquina maquina) {
        EtapaProducao etapa = null;
        Iterator<EtapaProducao> iterator = etapas.iterator();
        while(iterator.hasNext()) {
            etapa = iterator.next();
            if(etapa.maquina().equals(maquina))
                return etapa;
        }
        return etapa;
    }
    
    public boolean registarProducao(Producao producao){
        Preconditions.nonNull(producao, "Produção não pode ser null!");
        return producoes.add(producao);
    }
    
    public void registarProducao(Produto produto, double quantidade, Deposito deposito, String lote, Calendar dataRegisto) {
        // itemProduto, quantidade, deposito, String lote, DataRegisto dataRegisto, Double desvio
        registarProducao(new Producao(produto, quantidade, deposito, lote, dataRegisto, 0d));
    }
    
    public boolean registarEntregaProducao(Produto produto, double quantidade, Deposito deposito, Calendar dataRegisto) {
        //vai ao primeiro lote disponivel.
        if(lotes.size()==0) return false;
        Lote lote = lotes.remove(0);        
        registarProducao(new Producao(produto, quantidade, deposito, lote.codigo(), dataRegisto, 0d));
        return true;
    }
    
    public void registarProducao(String codLote, Produto produto, Double quantidade) {
        Lote lote = new Lote(codLote, produto);
        if(lotes.contains(lote)) {
            int index = lotes.indexOf(lote);
            lotes.get(index).adicionarQuantidade(quantidade);
        } else {
            lotes.add(lote);
        }
    }
    
    public boolean registarConsumo(Consumo consumo){
        Preconditions.nonNull(consumo, "Consumo não pode ser null!");
        return consumos.add(consumo);
    }
    
    public boolean registarEstorno(Estorno estorno){
        Preconditions.nonNull(estorno, "Estorno não pode ser null!");
        return estornos.add(estorno);
    }
    
    
    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof OrdemProducao)) {
            return false;
        }

        final ExecucaoOrdemProducao that = (ExecucaoOrdemProducao) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity());
    }

    @Override
    public Long identity() {
        return id;
    }
    
    public OrdemProducao ordemProducao(){
        return ordemProducao;
    }
    
    public Set<Consumo> consumos(){
        return consumos;
    }
    
    public Set<Estorno> estornos(){
        return estornos;
    }
    
    public Set<EtapaProducao> etapas(){
        return etapas;
    }
    
    public Set<Producao> producoes(){
        return producoes;
    }

    public void updateDesvios() {
        //desvio producoes
        double totalProduzido = 0;
        for(Producao p : producoes) {
            totalProduzido+=p.quantidade();
        }
        double qtdProdutoPrevista = ordemProducao().quantidade();
        double desvio = totalProduzido - qtdProdutoPrevista;
        for(Producao p: producoes) {
             //p.updateDesvio(desvio);
             p.updateDesvio((p.quantidade()*desvio)/totalProduzido);//desvio proporcional por lote
        }
        
        //Desvio componentes. 
        Set<Componente> componentes = ordemProducao().produto().fichaProducao().listaComponentes();
        
        Map<Componente, List<Consumo>> componenteConsumo = new HashMap();
        
        for(Componente componente : componentes) {
            componenteConsumo.put(componente, new ArrayList());
        }
        
        for(Consumo consumo: consumos) {
            for(Componente componente : componentes) {   
                if(consumoEDesteComponente(componente, consumo))
                    componenteConsumo.get(componente).add(consumo);
            }
        }
        
        for(Componente componente : componentes) {
            
            List<Consumo> listaConsumoComponente = componenteConsumo.get(componente);
            
            double totalConsumo = 0.0d;
            for(Consumo c:listaConsumoComponente) {
                totalConsumo += c.quantidade();
            }
            
            double totalComponenteEsperada = componente.quantidade() * ordemProducao().quantidade();
            double desvioTotalcomponente = totalConsumo - totalComponenteEsperada;
            
            for(Consumo c:listaConsumoComponente) {
                //c.updateDesvio(desvioTotalcomponente);
                double desv = (c.quantidade()*desvioTotalcomponente)/totalConsumo;                
                c.updateDesvio(desv);
            }
        }
        
        
    }
    
    private boolean consumoEDesteComponente(Componente componente, Consumo consumo) {
        
        if(componente.isProduto() && componente.produto().equals(consumo.produto())) {
            return true;
        } else if(!componente.isProduto() && componente.materiaPrima().equals(consumo.materiaPrima())) {
            return true;
        }

        return false;
    }

}