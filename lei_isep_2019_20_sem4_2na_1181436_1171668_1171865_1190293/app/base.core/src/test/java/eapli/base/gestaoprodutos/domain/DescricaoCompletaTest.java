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
public class DescricaoCompletaTest {
    /**
     * Teste descrição completa vazia
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarDescricaoCompletaVazia() {
        System.out.println("Teste Descrição completa vazia");
        DescricaoCompleta teste = new DescricaoCompleta("");
        assertEquals(null, teste);
    }
}
