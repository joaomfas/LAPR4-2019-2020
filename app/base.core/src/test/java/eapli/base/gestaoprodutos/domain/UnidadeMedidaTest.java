/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.gestaoprodutos.domain;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joaoferreira
 */
public class UnidadeMedidaTest {

    /**
     * Teste unidade de medida vazia
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarUnMedidaVazia() {
        System.out.println("Teste unidade de medida vazia");
        UnidadeMedida teste = new UnidadeMedida("");
        assertEquals(null, teste);
    }
    
    /**
     * Teste unidade de medida inválida
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarUnMedidaInvalida() {
        System.out.println("Teste unidade de medida inválida");
        UnidadeMedida teste = new UnidadeMedida("fasf");
        assertEquals(null, teste);
    }

}
