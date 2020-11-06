/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.gestaoprodutos.domain;

import eapli.base.gestaomateriasprimas.domain.MateriaPrima;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joaoferreira
 */
public class ComponenteTest {

    @Test(expected = IllegalArgumentException.class)
    public void verificarProdutoNull() {
        System.out.println("Teste item Produto null");
        Produto produto = null;
        new Componente(produto, 11D);
    }

    @Test(expected = IllegalArgumentException.class)
    public void verificarMateriaPrimaNull() {
        System.out.println("Teste item Produto null");
        MateriaPrima materiaPrima = null;
        new Componente(materiaPrima, 11D);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void verificarQuantidadeNegativa() {
        System.out.println("Teste item Produto null");
        Produto produto = new Produto(new CodigoFabrico("123"), new CodigoComercial("128"), new DescricaoBreve("descBreve"), 
                new DescricaoCompleta("descCompleta"),"catProduto", new UnidadeMedida("kg"));;
        new Componente(produto, -11D);
    }

}
