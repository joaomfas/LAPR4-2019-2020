package eapli.base.gestaomaquinas.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CodInterno implements ValueObject, Comparable<CodInterno> {

    private static final long serialVersionUID = 1L;

    @Column(unique = true, nullable = false)
    private final String codInterno;

    public CodInterno(final String codInterno) {
        Preconditions.ensure(!codInterno.isEmpty(), "Código Interno vazio!");
        Preconditions.ensure(codInterno.length()<=15, "Código Interno com mais do que 15 caracteres!");
        this.codInterno = codInterno;
    }

    protected CodInterno() {
        this.codInterno = null;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.codInterno);
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
        final CodInterno other = (CodInterno) obj;
        if (!Objects.equals(this.codInterno, other.codInterno)) {
            return false;
        }
        return true;
    }

    public String codInterno() {
        return codInterno;
    }
    
    @Override
    public String toString() {
        return codInterno;
    }

    @Override
    public int compareTo(CodInterno o) {
        return codInterno.compareTo(o.codInterno);
    }

}

