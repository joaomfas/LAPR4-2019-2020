package eapli.base.gestaolinhasproducao.domain;

import eapli.base.gestaomaquinas.domain.Maquina;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;

@Embeddable
public class Sequencia implements ValueObject{
    
    private static final long serialVersionUID = 1L;
    
    private Long numSquenciaMaquina;
    //@Column (nullable=false, unique=true)
    @OneToOne(cascade = CascadeType.ALL)
    private Maquina maquina;

    protected Sequencia() {
    }
    
    protected void requisitosCriacaoSequencia(Long numSequencia, Maquina maquina) {
        Preconditions.isPositive(numSequencia,"Número de sequência inválido.");
        Preconditions.nonNull(maquina,"Maquina inválida.");    
    }
    
    public Sequencia(Long numSquenciaMaquina, Maquina maquina) {
        requisitosCriacaoSequencia(numSquenciaMaquina, maquina);
        this.numSquenciaMaquina = numSquenciaMaquina;
        this.maquina = maquina;
    }
    
    public Long numSquenciaMaquina() {
        return this.numSquenciaMaquina;
    }

    public Maquina maquina() {
        return maquina;
    }
    
}
