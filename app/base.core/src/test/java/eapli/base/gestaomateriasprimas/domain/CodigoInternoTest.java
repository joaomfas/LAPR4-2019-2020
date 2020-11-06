package eapli.base.gestaomateriasprimas.domain;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mdias
 */
public class CodigoInternoTest {
    
    /**
     * Teste código interno de matéria prima vazio
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarCodInternoVazio() {
        System.out.println("Teste código interno de matéria prima vazio");
        CodigoInterno teste = new CodigoInterno("");
        assertEquals(null, teste);
    }
    
    
    /**
     * Teste código interno de matéria prima com menos de 15 caracteres
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarComprimentoCodInterno() {
        System.out.println("Teste código interno de matéria prima : excede tamanho maximo (15 + 1 caracteres)");
        CodigoInterno teste = new CodigoInterno("123456789ABCDEFG");
        assertEquals(null, teste);
    }
    
    /**
     * Teste código interno de matéria prima válido
     */
    @Test
    public void verificarComprimentoCodInternoValido() {
        System.out.println("Teste código interno de matéria prima válido");
        CodigoInterno teste = new CodigoInterno("123456789ABCDEF");
        assert(teste!=null);
    }
    
}
