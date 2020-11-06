package eapli.base.gestaoordensproducao.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Encomenda implements ValueObject {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private final String codigoEncomenda;

    public Encomenda(final String codigoEncomenda) {
        Preconditions.ensure(!codigoEncomenda.isEmpty(), "Código de Encomenda vazio!");
        Preconditions.nonNull(codigoEncomenda, "Código de encomenda null!");
        Preconditions.ensure(codigoEncomenda.length() <= 50, "Código de Encomenda com mais do que 50 caracteres!");
        this.codigoEncomenda = codigoEncomenda;
    }

    @Override
    public String toString() {
        return codigoEncomenda;
    }
    
    protected Encomenda() {
        this.codigoEncomenda = null;
    }
}
