package eapli.base.gestaomaquinas.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Descricao implements ValueObject, Comparable<Descricao>{
    private static final long serialVersionUID = 1L;
    
    @Column(nullable = false)
    private final String descricao;

    public Descricao() {
        this.descricao = "";
    }

    public Descricao(String descricao) {
        Preconditions.ensure(!descricao.isEmpty(), "Descrição vazia!");
        Preconditions.ensure(descricao.length()<=50, "Descrição com mais do que 50 caracteres!");
        this.descricao = descricao;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.descricao);
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
        final Descricao other = (Descricao) obj;
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        return true;
    }

    
    
    
    @Override
    public String toString() {
        return descricao;
    }


    @Override
    public int compareTo(Descricao o) {
        return descricao.compareTo(o.descricao);
    }


    
}
