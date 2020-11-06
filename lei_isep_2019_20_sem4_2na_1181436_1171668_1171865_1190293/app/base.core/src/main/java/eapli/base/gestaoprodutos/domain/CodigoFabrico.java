package eapli.base.gestaoprodutos.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CodigoFabrico implements ValueObject, Comparable<CodigoFabrico> {

    private static final long serialVersionUID = 1L;

    @Column(unique = true, nullable = false)
    private final String codigoFabrico;

    public CodigoFabrico(final String codigoFabrico) {
        Preconditions.ensure(!codigoFabrico.isEmpty(), "Código de Fabrico vazio!");
        Preconditions.ensure(codigoFabrico.length()<=15, "Código de Fabrico com mais do que 15 caracteres!");
        this.codigoFabrico = codigoFabrico;
    }

    protected CodigoFabrico() {
        this.codigoFabrico = null;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.codigoFabrico);
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
        final CodigoFabrico other = (CodigoFabrico) obj;
        if (!Objects.equals(this.codigoFabrico, other.codigoFabrico)) {
            return false;
        }
        return true;
    }

    public String codigoFabrico() {
        return codigoFabrico;
    }

    @Override
    public String toString() {
        return codigoFabrico;
    }
    
    @Override
    public int compareTo(CodigoFabrico o) {
        return codigoFabrico.compareTo(o.codigoFabrico);
    }
}
