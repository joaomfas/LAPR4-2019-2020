package eapli.base.gestaomaquinas.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Modelo implements ValueObject, Comparable<Modelo>{
    private static final long serialVersionUID = 1L;
    
    @Column(nullable = false)
    private final String modelo;

    public Modelo() {
        this.modelo = "";
    }

    public Modelo(String modelo) {
        Preconditions.ensure(!modelo.isEmpty(), "Sem modelo!");
        Preconditions.ensure(modelo.length()<=50, "Modelo com mais do que 15 caracteres!");
        this.modelo = modelo;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.modelo);
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
        final Modelo other = (Modelo) obj;
        if (!Objects.equals(this.modelo, other.modelo)) {
            return false;
        }
        return true;
    }

    
    
    
    @Override
    public String toString() {
        return modelo;
    }


    @Override
    public int compareTo(Modelo o) {
        return modelo.compareTo(o.modelo);
    }
}
