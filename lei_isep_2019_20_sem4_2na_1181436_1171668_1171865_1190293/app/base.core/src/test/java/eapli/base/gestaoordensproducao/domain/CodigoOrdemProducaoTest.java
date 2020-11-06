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
public class CodigoOrdemProducaoTest {
    /**
     * Teste código de ordem de produção vazio
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarCodOrdemProducaoVazio() {
        System.out.println("Teste código de ordem de produção vazio");
        CodigoOrdemProducao teste = new CodigoOrdemProducao("");
        assertEquals(null, teste);
    } 
}
