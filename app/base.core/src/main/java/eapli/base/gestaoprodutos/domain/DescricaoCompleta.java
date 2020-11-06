package eapli.base.gestaoprodutos.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DescricaoCompleta implements ValueObject {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private final String descricaoCompleta;

    public DescricaoCompleta(final String descricaoCompleta) {
        Preconditions.ensure(!descricaoCompleta.isEmpty(), "Descrição completa vazia!");
        Preconditions.ensure(descricaoCompleta.length()<=50, "Descrição Completa com mais do que 50 caracteres!");
        this.descricaoCompleta = descricaoCompleta;
    }

    protected DescricaoCompleta() {
        this.descricaoCompleta = "";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.descricaoCompleta);
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
        final DescricaoCompleta other = (DescricaoCompleta) obj;
        if (!Objects.equals(this.descricaoCompleta, other.descricaoCompleta)) {
            return false;
        }
        return true;
    }

    public String descricaoCompleta(){
        return this.descricaoCompleta;
    }
    
    @Override
    public String toString() {
        return this.descricaoCompleta;
    }
}