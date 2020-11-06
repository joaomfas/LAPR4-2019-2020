package eapli.base.gestaomaquinas.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class CodInternoTest {
    
    /**
     * Teste código interno vazio
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarCodInternoVazio() {
        System.out.println("Teste código interno vazio");
        CodInterno teste = new CodInterno("");
        assertEquals(null, teste);
    }
    
    /**
     * Teste código interno : excede tamanho maximo (15 + 1 caracteres)
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarCodInternoExcedeTamanhoMaximo() {
        System.out.println("Teste código interno : excede tamanho maximo (15 + 1 caracteres)");
        CodInterno teste = new CodInterno("123456789ABCDEFG");
        assertEquals(null, teste);
    }
    
    /**
     * Teste código interno válido
     */
    public void verificarCodInternoTamanhoMaximo() {
        System.out.println("Teste código interno válido");
        CodInterno teste = new CodInterno("123456789ABCDEF");
        assert(teste!=null);
    }
    
}
