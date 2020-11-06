
package eapli.base.gestaomateriasprimas.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CodigoCategoria implements ValueObject, Comparable<CodigoCategoria> {
    private static final long serialVersionUID = 1L;

    @Column(unique = true, nullable = false)
    private final String codigoCategoria;
    
    public CodigoCategoria(final String codigoCategoria) {
        Preconditions.ensure(!codigoCategoria.isEmpty(), "Código de Categoria vazio!");
        Preconditions.ensure(codigoCategoria.length()<=15, "Código de Categoria com mais do que 15 caracteres!");
        this.codigoCategoria = codigoCategoria;
    }

    protected CodigoCategoria() {
        this.codigoCategoria = "";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.codigoCategoria);
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
        final CodigoCategoria other = (CodigoCategoria) obj;
        if (!Objects.equals(this.codigoCategoria, other.codigoCategoria)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigoCategoria;
    }
    
    @Override
    public int compareTo(CodigoCategoria o) {
        return codigoCategoria.compareTo(o.codigoCategoria);
    }
    
}
