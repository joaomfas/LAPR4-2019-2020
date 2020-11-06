package eapli.base.gestaoprodutos.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CodigoComercial implements ValueObject, Comparable<CodigoComercial> {

    private static final long serialVersionUID = 1L;

    @Column(unique = true, nullable = false)
    private final String codigoComercial;

    public CodigoComercial(final String codigoComercial) {
        Preconditions.ensure(!codigoComercial.isEmpty(), "Código Comercial vazio!");
        Preconditions.ensure(codigoComercial.length()<=15, "Código Comercial com mais do que 15 caracteres!");
        this.codigoComercial = codigoComercial;
    }

    protected CodigoComercial() {
        this.codigoComercial = "";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.codigoComercial);
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
        final CodigoComercial other = (CodigoComercial) obj;
        if (!Objects.equals(this.codigoComercial, other.codigoComercial)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigoComercial;
    }

    @Override
    public int compareTo(CodigoComercial o) {
        return codigoComercial.compareTo(o.codigoComercial);
    }
}
