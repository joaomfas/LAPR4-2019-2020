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
public class DescricaoBreveTest {
    /**
     * Teste descrição breve vazia
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarDescricaoBreveVazia() {
        System.out.println("Teste Descrição breve vazia");
        DescricaoBreve teste = new DescricaoBreve("");
        assertEquals(null, teste);
    }
}
