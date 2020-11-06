package eapli.base.gestaomaquinas.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class DescricaoTest {
    
    public DescricaoTest() {
    }
    
    /**
     * Teste descricao válido
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarDescricaoNaoVazio() {
        System.out.println("Teste descrição vazio");
        Descricao teste = new Descricao("");
        assert(teste!=null);
    }
    
    /**
     * Teste descricao : excede tamanho maximo (50 + 1 caracteres)
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarDescricaoExcedeTamanhoMaximo() {
        System.out.println("Teste código interno : excede tamanho maximo (50 + 1 caracteres)");
        Descricao teste = new Descricao("123456789-123456789-123456789-123456789-123456789-1");
        assertEquals(null, teste);
    }
    
    /**
     * Teste descricao válido
     */
    @Test
    public void verificarDescricaoValido() {
        System.out.println("Teste descrição válido");
        Descricao teste = new Descricao("123456789ABCDEF");
        assert(teste!=null);
    }
}
