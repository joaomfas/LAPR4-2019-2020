/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.gestaoordensproducao.domain;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joaoferreira
 */
public class ProducaoTest {
    /**
     * Teste para parametros null
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificaParametrosNull() {
        System.out.println("Teste parametros Producao null");
        Producao teste = new Producao(null, null, null, null, null, null);
        assertEquals(null, teste);
    }
}
