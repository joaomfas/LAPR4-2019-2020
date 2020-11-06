/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.gestaoprodutos.domain;

import org.junit.Test;

/**
 *
 * @author joaoferreira
 */
public class ProdutoTest {

    /**
     * Teste sem código de produção
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarCodFabricoNaoVazio() {
        System.out.println("Teste código de produção null");
        new Produto(null, new CodigoComercial("128"), new DescricaoBreve("descBreve"), 
                new DescricaoCompleta("descCompleta"),"catProduto", new UnidadeMedida("kg"));
    }

    /**
     * Teste sem código comercial
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarCodComercialNaoVazio() {
        System.out.println("Teste código comercial null");
        new Produto(new CodigoFabrico("123"), null, new DescricaoBreve("descBreve"), 
                new DescricaoCompleta("descCompleta"),
                "catProduto", new UnidadeMedida("kg"));
    }

    /**
     * Teste sem descrição breve
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarDescBreveNaoVazia() {
        System.out.println("Teste descrição breve vazia");
        new Produto(new CodigoFabrico("123"), new CodigoComercial("128"), new DescricaoBreve(""), 
                new DescricaoCompleta("descCompleta"),
                "catProduto", new UnidadeMedida("kg"));
    }

    /**
     * Teste sem descrição completa
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarDescCompletaNaoVazia() {
        System.out.println("Teste descrição completa vazia");
        new Produto(new CodigoFabrico("123"), new CodigoComercial("128"), new DescricaoBreve("descBreve"), 
                new DescricaoCompleta(""),
                "catProduto", new UnidadeMedida("kg"));
    }

    /**
     * Teste sem categoria de produto
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarCatProdutoNaoVazia() {
        System.out.println("Teste categoria de produto vazia");
        new Produto(new CodigoFabrico("123"), new CodigoComercial("128"), new DescricaoBreve("descBreve"), 
                new DescricaoCompleta("descCompleta"),
                "", new UnidadeMedida("kg"));
    }

    /**
     * Teste sem unidade de medida
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarUnMedidaVazia() {
        System.out.println("Teste unidade de medida null");
        new Produto(new CodigoFabrico("123"), new CodigoComercial("128"), new DescricaoBreve("descBreve"), 
                new DescricaoCompleta("descCompleta"),
                "cat", null);
    }
}