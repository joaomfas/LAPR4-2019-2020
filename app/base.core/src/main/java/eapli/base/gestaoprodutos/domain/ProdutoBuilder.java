package eapli.base.gestaoprodutos.domain;

import eapli.framework.domain.model.DomainFactory;


public class ProdutoBuilder implements DomainFactory<Produto> {

    private CodigoFabrico codigoFabrico;
    private CodigoComercial codigoComercial;
    private DescricaoBreve descricaoBreve;
    private DescricaoCompleta descricaoCompleta;
    private String categoria;
    private UnidadeMedida unidadeMedida;
    
    public ProdutoBuilder comCodigoFabrico(final String codigoFabrico) {
        this.codigoFabrico = new CodigoFabrico(codigoFabrico);
        return this;
    }
    
    public ProdutoBuilder comCodigoComercial(final String codigoComercial) {
        this.codigoComercial = new CodigoComercial(codigoComercial);
        return this;
    }
    
    public ProdutoBuilder comDescricaoBreve(final String descricaoBreve) {
        this.descricaoBreve = new DescricaoBreve(descricaoBreve);
        return this;
    }
    
    public ProdutoBuilder comDescricaoCompleta(final String descricaoCompleta) {
        this.descricaoCompleta = new DescricaoCompleta(descricaoCompleta);
        return this;
    }
    
    public ProdutoBuilder comCategoria(final String categoria) {
        this.categoria = categoria;
        return this;
    }
    
    public ProdutoBuilder comUnidadeMedida(final String unidadeMedida) {
        this.unidadeMedida = new UnidadeMedida(unidadeMedida);
        return this;
    }
    
    @Override
    public Produto build() {
        return new Produto(codigoFabrico, codigoComercial, descricaoBreve, descricaoCompleta, categoria, unidadeMedida);
    }

}
