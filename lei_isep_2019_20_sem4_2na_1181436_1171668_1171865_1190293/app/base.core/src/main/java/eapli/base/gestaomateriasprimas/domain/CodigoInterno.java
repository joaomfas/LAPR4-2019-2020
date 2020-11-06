package eapli.base.gestaomateriasprimas.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CodigoInterno implements ValueObject, Comparable<CodigoInterno> {

    private static final long serialVersionUID = 1L;

    @Column(unique = true, nullable = false)
    private final String codigoInterno;
    
    public CodigoInterno(final String codigoInterno) {
        Preconditions.ensure(!codigoInterno.isEmpty(), "Código Interno vazio!");
        Preconditions.ensure(codigoInterno.length()<=15, "Código interno de matéria prima com mais do que 15 caracteres!");
        this.codigoInterno = codigoInterno;
    }

    protected CodigoInterno() {
        this.codigoInterno = "";
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.codigoInterno);
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
        final CodigoInterno other = (CodigoInterno) obj;
        if (!Objects.equals(this.codigoInterno, other.codigoInterno)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigoInterno;
    }
    
    @Override
    public int compareTo(CodigoInterno o) {
        return codigoInterno.compareTo(o.codigoInterno);
    }
    
}
