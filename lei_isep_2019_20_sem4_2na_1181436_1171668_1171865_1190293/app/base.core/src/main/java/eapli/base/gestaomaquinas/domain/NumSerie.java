package eapli.base.gestaomaquinas.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class NumSerie implements ValueObject, Comparable<NumSerie> {
    private static final long serialVersionUID = 1L;

    @Column(unique = true, nullable = false)
    private final String numSerie;

    public NumSerie(final String numSerie) {
        Preconditions.ensure(!numSerie.isEmpty(), "Sem Número de série!");
        Preconditions.ensure(numSerie.length()<=15, "Número série com mais do que 15 caracteres!");
        this.numSerie = numSerie;
    }

    protected NumSerie() {
        this.numSerie = "";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.numSerie);
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
        final NumSerie other = (NumSerie) obj;
        if (!Objects.equals(this.numSerie, other.numSerie)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return numSerie;
    }

    @Override
    public int compareTo(NumSerie o) {
        return numSerie.compareTo(o.numSerie);
    }

   
}
