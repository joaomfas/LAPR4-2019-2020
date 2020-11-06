package eapli.base.gestaodepositos.domain;

import eapli.base.gestaomaquinas.domain.Descricao;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Version;

@Entity
public class Deposito implements AggregateRoot<CodigoDeposito> {
    private static final long serialVersionUID = 1L;

    @Version
    private Long version;
    
    @EmbeddedId
    private CodigoDeposito codigoDeposito;
    @Column(nullable = false)
    private Descricao descricao;

    protected Deposito() {
    }

    public Deposito(final CodigoDeposito codigoDeposito, final Descricao descricao) {
        requisitosDeposito(codigoDeposito, descricao);
        this.codigoDeposito = codigoDeposito;
        this.descricao = descricao;
    }
    
    private void requisitosDeposito(final CodigoDeposito codigoDeposito, final Descricao descricao){
        Preconditions.noneNull(codigoDeposito, descricao);
    }

    public Descricao descricao() {
        return this.descricao;
    }

    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Deposito)) {
            return false;
        }

        final Deposito that = (Deposito) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity()) && codigoDeposito.equals(that.codigoDeposito)
                && descricao.equals(that.descricao);
    }

    @Override
    public CodigoDeposito identity() {
        return this.codigoDeposito;
    }
}
