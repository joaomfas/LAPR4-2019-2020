package eapli.base.gestaodepositos.domain;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mdias
 */
public class CodigoDepositoTest {
    
    /**
     * Teste código de depósito vazio
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarCodDepositoVazio() {
        System.out.println("Teste código de depósito vazio");
        CodigoDeposito teste = new CodigoDeposito("");
        assertEquals(null, teste);
    }
    
    /**
     * Teste código de depósito com menos de 15 caracteres
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarComprimentoCodDeposito() {
        System.out.println("Teste código depósito : excede tamanho maximo (15 + 1 caracteres)");
        CodigoDeposito teste = new CodigoDeposito("123456789ABCDEFG");
        assertEquals(null, teste);
    }
    
    /**
     * Teste código de depósito válido
     */
    @Test
    public void verificarComprimentoCodDepositoValido() {
        System.out.println("Teste código depósito válido");
        CodigoDeposito teste = new CodigoDeposito("123456789ABCDEF");
        assert(teste!=null);
    }
    
}
