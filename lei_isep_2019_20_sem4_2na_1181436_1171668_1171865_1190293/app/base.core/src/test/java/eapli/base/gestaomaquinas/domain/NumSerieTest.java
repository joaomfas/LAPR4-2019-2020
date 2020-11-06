package eapli.base.gestaomaquinas.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class NumSerieTest {
    
    public NumSerieTest() {
    }

    /**
     * Teste NumSerie válido
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarNumSerieNaoVazio() {
        System.out.println("Teste numSerie vazio");
        NumSerie teste = new NumSerie("");
        assert(teste!=null);
    }
    
    /**
     * Teste NumSerie : excede tamanho maximo (50 + 1 caracteres)
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarNumSerieExcedeTamanhoMaximo() {
        System.out.println("Teste numSerie : excede tamanho maximo (50 + 1 caracteres)");
        NumSerie teste = new NumSerie("123456789-123456789-123456789-123456789-123456789-1");
        assertEquals(null, teste);
    }
    
    /**
     * Teste NumSerie válido
     */
    @Test
    public void verificarNumSerieValido() {
        System.out.println("Teste numSerie válido");
        NumSerie teste = new NumSerie("123456789ABCDEF");
        assert(teste!=null);
    }
}
