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
public class EncomendaTest {

    /**
     * Teste para código de encomenda vazio
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificaCodigoVazio() {
        System.out.println("Teste código de encomenda vazio");
        Encomenda teste = new Encomenda("");
        assertEquals(null, teste);
    }
}
