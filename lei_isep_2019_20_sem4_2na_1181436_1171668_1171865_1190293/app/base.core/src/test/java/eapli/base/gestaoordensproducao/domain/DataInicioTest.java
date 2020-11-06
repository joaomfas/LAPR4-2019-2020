/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.gestaoordensproducao.domain;

import java.util.Calendar;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author joaoferreira
 */
public class DataInicioTest {
    /**
     * Teste para formato de data inválido
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarFormatoDataInvalido() {
        System.out.println("Teste formato de data inválido");
        DataInicio teste = new DataInicio("1993/06/22");
        assertEquals(null, teste);
    }
}
