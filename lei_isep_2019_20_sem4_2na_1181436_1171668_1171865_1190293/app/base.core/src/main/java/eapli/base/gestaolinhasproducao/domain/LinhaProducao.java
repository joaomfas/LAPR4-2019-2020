package eapli.base.gestaolinhasproducao.domain;

import eapli.base.gestaomaquinas.domain.Estado;
import eapli.base.gestaomaquinas.domain.Maquina;
import eapli.base.gestaomaquinas.domain.NumeroIdentificacaoUnico;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class LinhaProducao implements AggregateRoot<String> {
    
    private static final long serialVersionUID = 1L;

    @Version
    private Long version;
    
    @Id
    private String id; //id da linha de produção
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Sequencia> listaSequencia;
    private EstadoProcessamentoRecorrente estadoProcessamentoRecorrente;
    private DataProcessamento dataProcessamento;

    protected LinhaProducao() {
    }

    public LinhaProducao(String id) {
        requisitosCriacaoSequencia(id);
        this.id = id;
        this.listaSequencia = new ArrayList<Sequencia>();
        this.dataProcessamento = null;
        this.estadoProcessamentoRecorrente = EstadoProcessamentoRecorrente.INATIVO;
    }

    private void requisitosCriacaoSequencia(String id) {
        Preconditions.nonEmpty(id, "ID vazio!");
    }
    
    
    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof LinhaProducao)) {
            return false;
        }

        final LinhaProducao that = (LinhaProducao) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity());
    }

    @Override
    public String identity() {
        return this.id;
    }

    public List<Sequencia> listaSequencia() {
        return listaSequencia;
    }
    
    public DataProcessamento dataProcessamento(){
        return dataProcessamento;
    }
    
    public EstadoProcessamentoRecorrente estadoProcessamentoRecorrente(){
        return estadoProcessamentoRecorrente;
    }
    
    public boolean adicionarMaquina(Sequencia sequencia) {
        Preconditions.ensure(posicaoLivre(sequencia), "Posicao já ocupada");
        return this.listaSequencia.add(sequencia);
    }
    
    
    private boolean posicaoLivre(Sequencia sequencia) {
        for(Sequencia seq:listaSequencia) {
            if(seq.numSquenciaMaquina().equals(sequencia.numSquenciaMaquina())) {
                return false;
            }
        }
        return true;
    }

    public boolean contemMaquinaComNumUnico(NumeroIdentificacaoUnico numeroIdentificacaoUnico) {
        for(Sequencia seq : listaSequencia){
            if(seq.maquina().numeroIdentificacaoUnico().equals(numeroIdentificacaoUnico)){
                return true;
            }
        }
        return false;
    }
    
    public Maquina maquina(NumeroIdentificacaoUnico numeroIdentificacaoUnico) {
        for(Sequencia seq : listaSequencia){
            if(seq.maquina().numeroIdentificacaoUnico().equals(numeroIdentificacaoUnico)){
                return seq.maquina();
            }
        }
        return null;
    }

    public List<Maquina> maquinas() {
        List<Maquina> maquinas = new ArrayList();
        for(Sequencia seq : listaSequencia){
            maquinas.add(seq.maquina());            
        }
        return maquinas;
    }
    
    public List<Maquina> maquinasMonitorizadas() {
        List<Maquina> maquinasMonitorizadas = new ArrayList();
        for(Sequencia seq : listaSequencia){
            if(seq.maquina().estado() == Estado.DISPONIVEL) {
                maquinasMonitorizadas.add(seq.maquina());
            }
        }
        return maquinasMonitorizadas;
    }
    
    public void updateDataProcessamento(DataProcessamento dataProcessamento) {
        Preconditions.nonNull(dataProcessamento, "Data de processamento não pode ser null!");
        this.dataProcessamento = dataProcessamento;
    }
    
    public void updateEstadoProcessamentoRecorrente(EstadoProcessamentoRecorrente estado) {
        Preconditions.nonNull(estado, "Estado do processamento recorrente não pode ser null!");
        Preconditions.ensure((!this.estadoProcessamentoRecorrente.equals(estado)), "O novo estado tem de ser diferente!");
        this.estadoProcessamentoRecorrente = estado;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LinhaProducao other = (LinhaProducao) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
