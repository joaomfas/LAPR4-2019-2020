package eapli.base.gestaoordensproducao.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class CodigoOrdemProducao implements ValueObject, Comparable<CodigoOrdemProducao> {

    private static final long serialVersionUID = 1L;

    @Column(unique = true, nullable = false)
    private final String codigoOrdemProducao;

    public CodigoOrdemProducao(final String codigoOrdemProducao) {
        Preconditions.ensure(!codigoOrdemProducao.isEmpty(), "Código de Ordem de Produção vazio!");
        Preconditions.ensure(codigoOrdemProducao.length()<=15, "Código de Ordem de Produção com mais do que 15 caracteres!");
        this.codigoOrdemProducao = codigoOrdemProducao;
    }

    protected CodigoOrdemProducao() {
        this.codigoOrdemProducao = null;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.codigoOrdemProducao);
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
        final CodigoOrdemProducao other = (CodigoOrdemProducao) obj;
        if (!Objects.equals(this.codigoOrdemProducao, other.codigoOrdemProducao)) {
            return false;
        }
        return true;
    }

    public String codigoOrdemProducao() {
        return codigoOrdemProducao;
    }

    @Override
    public String toString() {
        return codigoOrdemProducao;
    }

    @Override
    public int compareTo(CodigoOrdemProducao o) {
        return codigoOrdemProducao.compareTo(o.codigoOrdemProducao);
    }
}
