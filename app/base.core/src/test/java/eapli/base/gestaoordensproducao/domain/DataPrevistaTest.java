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
public class DataPrevistaTest {

    /**
     * Teste para formato de data inválido
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarFormatoDataInvalido() {
        System.out.println("Teste formato de data inválido");
        DataPrevista teste = new DataPrevista("2021/06/22");
        assertEquals(null, teste);
    }
    
    /**
     * Teste para data no passado
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificaDataNoPassado() {
        System.out.println("Teste formato de data inválido");
        DataPrevista teste = new DataPrevista("01/01/2020");
        assertEquals(null, teste);
    }
}
