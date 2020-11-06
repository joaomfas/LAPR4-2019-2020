package eapli.base.gestaomaquinas.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class MarcaTest {
    
    public MarcaTest() {
    }

    /**
     * Teste Marca válido
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarMarcaNaoVazio() {
        System.out.println("Teste Marca vazio");
        Marca teste = new Marca("");
        assert(teste!=null);
    }
    
    /**
     * Teste Marca : excede tamanho maximo (50 + 1 caracteres)
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarMarcaExcedeTamanhoMaximo() {
        System.out.println("Teste Marca : excede tamanho maximo (50 + 1 caracteres)");
        Marca teste = new Marca("123456789-123456789-123456789-123456789-123456789-1");
        assertEquals(null, teste);
    }
    
    /**
     * Teste Marca válido
     */
    @Test
    public void verificarMarcaValido() {
        System.out.println("Teste Marca válido");
        Marca teste = new Marca("123456789ABCDEF");
        assert(teste!=null);
    }
    
}
