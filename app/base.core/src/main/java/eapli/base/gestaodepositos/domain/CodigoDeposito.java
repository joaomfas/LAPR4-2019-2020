package eapli.base.gestaodepositos.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CodigoDeposito implements ValueObject, Comparable<CodigoDeposito> {
    private static final long serialVersionUID = 1L;

    @Column(unique = true, nullable = false)
    private final String codigoDeposito;
    
    public CodigoDeposito(final String codigoDeposito) {
        Preconditions.ensure(!codigoDeposito.isEmpty(), "Código de Depósito vazio!");
        Preconditions.ensure(codigoDeposito.length()<=15, "Código de Depósito com mais do que 15 caracteres!");
        this.codigoDeposito = codigoDeposito;
    }

    protected CodigoDeposito() {
        this.codigoDeposito = "";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.codigoDeposito);
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
        final CodigoDeposito other = (CodigoDeposito) obj;
        if (!Objects.equals(this.codigoDeposito, other.codigoDeposito)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigoDeposito;
    }
    
    @Override
    public int compareTo(CodigoDeposito o) {
        return codigoDeposito.compareTo(o.codigoDeposito);
    }
}
