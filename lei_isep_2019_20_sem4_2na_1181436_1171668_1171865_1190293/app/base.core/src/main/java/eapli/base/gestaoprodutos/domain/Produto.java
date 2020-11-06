package eapli.base.gestaoprodutos.domain;

import eapli.base.gestaoprodutos.DTO.ProdutoDTO;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.validations.Preconditions;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Version;

@Entity
public class Produto implements AggregateRoot<CodigoFabrico>, DTOable<ProdutoDTO> {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @EmbeddedId
    private CodigoFabrico codigoFabrico;
    private CodigoComercial codigoComercial;
    private DescricaoBreve descricaoBreve;
    private DescricaoCompleta descricaoCompleta;
    private String categoria;
    private UnidadeMedida unidadeMedida;
    private FichaProducao fichaProducao;
    private boolean hasFichaProducao;

    protected Produto() {
    }

    public Produto(CodigoFabrico codigoFabrico, CodigoComercial codigoComercial,
            DescricaoBreve descricaoBreve, DescricaoCompleta descricaoCompleta, String categoria,
            UnidadeMedida unidadeMedida) {

        requisitosCriacaoProduto(codigoFabrico, codigoComercial, descricaoBreve, descricaoCompleta, categoria,
                unidadeMedida);
        this.codigoFabrico = codigoFabrico;
        this.codigoComercial = codigoComercial;
        this.descricaoBreve = descricaoBreve;
        this.descricaoCompleta = descricaoCompleta;
        this.categoria = categoria;
        this.unidadeMedida = unidadeMedida;
        this.hasFichaProducao = false;
        this.fichaProducao = null;
    }

    private void requisitosCriacaoProduto(CodigoFabrico codigoFabrico, CodigoComercial codigoComercial,
            DescricaoBreve descricaoBreve, DescricaoCompleta descricaoCompleta, String categoria, UnidadeMedida unidadeMedida) {
        
        Preconditions.nonNull(codigoFabrico, "Codigo fabrico null!");
        Preconditions.nonNull(codigoComercial, "Codigo comercial null!");
        Preconditions.nonNull(unidadeMedida, "Unidade de medida null!");
        Preconditions.nonNull(descricaoBreve, "Descricao breve null!");
        Preconditions.nonNull(descricaoCompleta, "Descricao completa null!");
        Preconditions.nonEmpty(categoria, "Categoria vazia!");
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
        if (!(other instanceof Produto)) {
            return false;
        }

        final Produto that = (Produto) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity()) && codigoComercial.equals(that.codigoComercial)
                && descricaoBreve.equals(that.descricaoBreve) && descricaoCompleta.equals(that.descricaoCompleta)
                && categoria.equals(that.categoria) && unidadeMedida.equals(that.unidadeMedida);
    }

    @Override
    public CodigoFabrico identity() {
        return this.codigoFabrico;
    }

    public CodigoComercial codigoComercial() {
        return this.codigoComercial;
    }

    public DescricaoBreve descricaoBreve() {
        return this.descricaoBreve;
    }

    public DescricaoCompleta descricaoCompleta() {
        return this.descricaoCompleta;
    }

    public String categoria() {
        return this.categoria;
    }
    
    public void updateFichaProducao(FichaProducao fichaProducao){
        this.fichaProducao = fichaProducao;
        if(!hasFichaProducao){
            toggleHasFichaProducao();
        }
    }

    public UnidadeMedida unidadeMedida() {
        return this.unidadeMedida;
    }
    
    public boolean hasFichaProducao(){
        return this.hasFichaProducao;
    }
    
    public void toggleHasFichaProducao(){
        this.hasFichaProducao = !this.hasFichaProducao;
    }
    
    public FichaProducao fichaProducao() {
        return this.fichaProducao;
    }

    @Override
    public ProdutoDTO toDTO() {
        return new ProdutoDTO(identity().toString(), codigoComercial().toString(), descricaoBreve.toString(),
                descricaoCompleta.toString(), categoria, unidadeMedida.toString());
    }
}
