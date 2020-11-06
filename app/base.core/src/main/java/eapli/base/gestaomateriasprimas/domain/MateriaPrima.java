package eapli.base.gestaomateriasprimas.domain;

import eapli.base.gestaomaquinas.domain.Descricao;
import eapli.base.gestaoprodutos.domain.UnidadeMedida;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Entity
public class MateriaPrima implements AggregateRoot<CodigoInterno> {
    
    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @EmbeddedId
    private CodigoInterno codigo;
    @ManyToOne()
    private CategoriaMateriaPrima categoriaMP;
    @Column(nullable = false)
    private Descricao descricao;
    @Column(nullable = false)
    @Basic(fetch=FetchType.LAZY)
    private FichaTecnica fichaTecnica;
    private UnidadeMedida unidadeMedida;
    
    protected MateriaPrima() {
    }
    
    public MateriaPrima(CodigoInterno codigo, CategoriaMateriaPrima categoriaMP,
            Descricao descricao, FichaTecnica fichaTecnica, UnidadeMedida unidadeMedida) {

        requisitosCriacaoMateriaPrima(codigo, categoriaMP, descricao, fichaTecnica, unidadeMedida);
        this.codigo = codigo;
        this.categoriaMP = categoriaMP;
        this.descricao = descricao;
        this.fichaTecnica = fichaTecnica;
        this.unidadeMedida = unidadeMedida;
    }
    
    private void requisitosCriacaoMateriaPrima(CodigoInterno codigo, CategoriaMateriaPrima categoriaMP,
            Descricao descricao, FichaTecnica fichaTecnica, UnidadeMedida unidadeMedida) {
        
        Preconditions.noneNull(codigo, categoriaMP, descricao, fichaTecnica, unidadeMedida);
    }
    
    public CodigoInterno codigo() {
        return this.codigo;
    }

    public Descricao descricao() {
        return this.descricao;
    }

    public CategoriaMateriaPrima categoriaMP() {
        return this.categoriaMP;
    }
    
    public FichaTecnica fichaTecnica() {
        return this.fichaTecnica;
    }

    public UnidadeMedida unidadeMedida() {
        return this.unidadeMedida;
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
        if (!(other instanceof MateriaPrima)) {
            return false;
        }

        final MateriaPrima that = (MateriaPrima) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity()) && codigo.equals(that.codigo)
                && categoriaMP.equals(that.categoriaMP) && descricao.equals(that.descricao)
                && fichaTecnica.equals(that.fichaTecnica) && unidadeMedida.equals(that.unidadeMedida);
    }

    @Override
    public CodigoInterno identity() {
        return this.codigo;
    }

}
