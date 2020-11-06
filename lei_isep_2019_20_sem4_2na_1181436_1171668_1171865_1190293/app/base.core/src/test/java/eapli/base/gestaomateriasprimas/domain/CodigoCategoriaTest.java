package eapli.base.gestaomateriasprimas.domain;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mdias
 */
public class CodigoCategoriaTest {
    
    /**
     * Teste código de categoria vazio
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarCodCategoriaVazio() {
        System.out.println("Teste código de categoria vazio");
        CodigoCategoria teste = new CodigoCategoria("");
        assertEquals(null, teste);
    }
    
    /**
     * Teste código de categoria com menos de 15 caracteres
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarComprimentoCodCategoria() {
        System.out.println("Teste código categoria : excede tamanho maximo (15 + 1 caracteres)");
        CodigoCategoria teste = new CodigoCategoria("123456789ABCDEFG");
        assertEquals(null, teste);
    }
    
    /**
     * Teste código de categoria válido
     */
    @Test
    public void verificarComprimentoCodCategoriaValido() {
        System.out.println("Teste código categoria válido");
        CodigoCategoria teste = new CodigoCategoria("123456789ABCDEF");
        assert(teste!=null);
    }
    
}
