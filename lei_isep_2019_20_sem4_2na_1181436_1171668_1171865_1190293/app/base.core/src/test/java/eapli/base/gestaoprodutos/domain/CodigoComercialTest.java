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
public class CodigoComercialTest {

    /**
     * Teste código de fabrico vazio
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarCodComercialVazio() {
        System.out.println("Teste código comercial vazio");
        CodigoComercial teste = new CodigoComercial("");
        assertEquals(null, teste);
    }

}
