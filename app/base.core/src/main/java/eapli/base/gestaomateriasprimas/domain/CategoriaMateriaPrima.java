package eapli.base.gestaomateriasprimas.domain;

import eapli.base.gestaomaquinas.domain.Descricao;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Version;

@Entity
public class CategoriaMateriaPrima implements AggregateRoot<CodigoCategoria> {
    
    private static final long serialVersionUID = 1L;

    @Version
    private Long version;
    
    @EmbeddedId
    private CodigoCategoria codigoCategoria;
    @Column(nullable = false)
    private Descricao descricao;

    protected CategoriaMateriaPrima() {
    }

    public CategoriaMateriaPrima(final CodigoCategoria codigoCategoria, final Descricao descricao) {
        requisitosCategoriaMateriaPrima(codigoCategoria, descricao);
        this.codigoCategoria = codigoCategoria;
        this.descricao = descricao;
    }
    
    private void requisitosCategoriaMateriaPrima(final CodigoCategoria codigoCategoria, final Descricao descricao){
        Preconditions.noneNull(codigoCategoria, descricao);
    }
    
    public CodigoCategoria codigoCategoria() {
        return this.codigoCategoria;
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
        if (!(other instanceof CategoriaMateriaPrima)) {
            return false;
        }

        final CategoriaMateriaPrima that = (CategoriaMateriaPrima) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity()) && codigoCategoria.equals(that.codigoCategoria)
                && descricao.equals(that.descricao);
    }

    @Override
    public CodigoCategoria identity() {
        return this.codigoCategoria;
    }
}
