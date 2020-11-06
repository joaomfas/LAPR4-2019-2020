package eapli.base.gestaomaquinas.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class ModeloTest {
    
    public ModeloTest() {
    }

    /**
     * Teste Modelo válido
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarModeloNaoVazio() {
        System.out.println("Teste Modelo vazio");
        Modelo teste = new Modelo("");
        assert(teste!=null);
    }
    
    /**
     * Teste Modelo : excede tamanho maximo (50 + 1 caracteres)
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarModeloExcedeTamanhoMaximo() {
        System.out.println("Teste Modelo : excede tamanho maximo (50 + 1 caracteres)");
        Modelo teste = new Modelo("123456789-123456789-123456789-123456789-123456789-1");
        assertEquals(null, teste);
    }
    
    /**
     * Teste Modelo válido
     */
    @Test
    public void verificarModeloValido() {
        System.out.println("Teste Modelo válido");
        Modelo teste = new Modelo("123456789ABCDEF");
        assert(teste!=null);
    }
}
