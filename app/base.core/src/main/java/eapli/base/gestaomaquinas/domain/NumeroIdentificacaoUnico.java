package eapli.base.gestaomaquinas.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class NumeroIdentificacaoUnico implements ValueObject {

    @Column(nullable = false)
    private final String numeroIdentificacaoUnico;

    private NumeroIdentificacaoUnico() {
        numeroIdentificacaoUnico = "";
    }

    public NumeroIdentificacaoUnico(String numeroIdentificacaoUnico) {
        Preconditions.nonNull(numeroIdentificacaoUnico, "O número de Identificação único inválido");
        Preconditions.ensure(numeroValido(numeroIdentificacaoUnico), "O número de Identificação único inválido, introduza um número entre 1 e 65535.");
        this.numeroIdentificacaoUnico = numeroIdentificacaoUnico;
    }

    private boolean numeroValido(String numIdUnico) {
        Integer niu = Integer.parseInt(numIdUnico);
        return niu > 0 && niu < 65535;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.numeroIdentificacaoUnico);
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
        final NumeroIdentificacaoUnico other = (NumeroIdentificacaoUnico) obj;
        if (!Objects.equals(this.numeroIdentificacaoUnico, other.numeroIdentificacaoUnico)) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        return numeroIdentificacaoUnico;
    }
}
