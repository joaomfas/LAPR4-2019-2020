package eapli.base.gestaoprodutos.DTO;

import eapli.framework.representations.dto.DTO;

@SuppressWarnings("squid:ClassVariableVisibilityCheck")
public class ProdutoDTO implements DTO {

   public ProdutoDTO(String codigoFabrico, String codigoComercial,
            String descricaoBreve, String descricaoCompleta, String categoria,
            String unidadeMedida) {

        this.codigoFabrico = codigoFabrico;
        this.codigoComercial = codigoComercial;
        this.descricaoBreve = descricaoBreve;
        this.descricaoCompleta = descricaoCompleta;
        this.categoria = categoria;
        this.unidadeMedida = unidadeMedida;
        this.fichaProducao = null;
    }

    public ProdutoDTO() {
        // empty
    }

    public String codigoFabrico;
    public String codigoComercial;
    public String descricaoBreve;
    public String descricaoCompleta;
    public String categoria;
    public String unidadeMedida;
    public String fichaProducao;
}
