package eapli.base.gestaoordensproducao.domain;

import eapli.base.gestaomaquinas.domain.Maquina;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import java.time.Duration;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class EtapaProducao implements ValueObject{
    private static final long serialVersionUID = 1L;
    
    @ManyToOne()
    private final Maquina maquina;
    private Long tempoBruto;
    private Long tempoEfetivo;

    private Long inicioAtividade;
    private Long fimAtividade;
    private Long paragem;
    private Long tempoParado;
    
    public EtapaProducao() {
        maquina=null;
        tempoBruto=0l;
        tempoEfetivo=0l;
        inicioAtividade=0l;
        fimAtividade=0l;
        paragem=0l;
        tempoParado=0l;
    }
    
    public EtapaProducao(Maquina maquina, Duration tempoBruto, Duration tempoEfetivo) {
        requisitosEtapaProducao(maquina, tempoBruto, tempoEfetivo);
        this.maquina = maquina;
        this.tempoBruto = tempoBruto.toNanos();
        this.tempoEfetivo = tempoEfetivo.toNanos();
    }
    
    private void requisitosEtapaProducao(Maquina maquina, Duration tempoBruto, Duration tempoEfetivo) {
        Preconditions.nonNull(maquina, "Máquina não pode ser null!");
        Preconditions.nonNull(tempoBruto, "Tempo bruto não pode ser null!");
        Preconditions.nonNull(tempoEfetivo, "Tempo efetivo não pode ser null!");
    }

    public EtapaProducao(Maquina maquina) {
        this.maquina = maquina;
        tempoBruto=0l;
        tempoEfetivo=0l;
        inicioAtividade=0l;
        fimAtividade=0l;
        paragem=0l;
        tempoParado=0l;
    }
    
    public void registarInicioAtividade(Long inicioAtividade) {
        this.inicioAtividade = inicioAtividade;
    }

    public void registarFimAtividade(Long fimAtividade) {
        this.fimAtividade = fimAtividade;
        this.tempoBruto = this.fimAtividade - this.inicioAtividade;
        this.tempoEfetivo = this.tempoBruto - tempoParado;
    }
    
    public void registarParagem(Long paragem) {
        this.paragem = paragem;
    }
    
    public void registarRetoma(Long retomaAtividade) {
        this.tempoParado += retomaAtividade-this.paragem;
    }
    
    public Maquina maquina() {
        return maquina;
    }

    public Duration tempoBruto() {
        return Duration.ofNanos(tempoBruto);
    }

    public Duration tempoEfetivo() {
        return Duration.ofNanos(tempoEfetivo);
    }
    
    public boolean concluido() {
        if(tempoBruto!=0l) return true;
        return false;
    }
    
}
