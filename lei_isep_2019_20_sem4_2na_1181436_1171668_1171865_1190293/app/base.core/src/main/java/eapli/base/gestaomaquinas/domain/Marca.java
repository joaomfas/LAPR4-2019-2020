package eapli.base.gestaomaquinas.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Marca implements ValueObject, Comparable<Marca> {
    private static final long serialVersionUID = 1L;
    
    @Column(nullable = false)
    private final String marca;

    public Marca() {
        this.marca = "";        
    }

    public Marca(String marca) {
        Preconditions.ensure(!marca.isEmpty(), "Sem marca!");
        Preconditions.ensure(marca.length()<=50, "Marca com mais do que 15 caracteres!");
        this.marca = marca;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.marca);
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
        final Marca other = (Marca) obj;
        if (!Objects.equals(this.marca, other.marca)) {
            return false;
        }
        return true;
    }

    
    
    
    @Override
    public String toString() {
        return marca;
    }


    @Override
    public int compareTo(Marca o) {
        return marca.compareTo(o.marca);
    }
}
