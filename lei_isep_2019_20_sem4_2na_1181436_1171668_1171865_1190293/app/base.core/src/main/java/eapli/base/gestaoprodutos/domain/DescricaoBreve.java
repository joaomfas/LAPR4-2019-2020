package eapli.base.gestaoprodutos.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DescricaoBreve implements ValueObject, Comparable<DescricaoBreve> {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private final String descricaoBreve;

    public DescricaoBreve(final String descricaoBreve) {
        Preconditions.ensure(!descricaoBreve.isEmpty(), "Código Comercial vazio!");
        Preconditions.ensure(descricaoBreve.length()<=50, "Descrição Breve com mais do que 50 caracteres!");
        this.descricaoBreve = descricaoBreve;
    }

    protected DescricaoBreve() {
        this.descricaoBreve = "";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.descricaoBreve);
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
        final DescricaoBreve other = (DescricaoBreve) obj;
        if (!Objects.equals(this.descricaoBreve, other.descricaoBreve)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descricaoBreve;
    }

    @Override
    public int compareTo(DescricaoBreve o) {
        return descricaoBreve.compareTo(o.descricaoBreve);
    }
}
