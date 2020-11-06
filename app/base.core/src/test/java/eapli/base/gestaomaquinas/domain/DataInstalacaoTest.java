package eapli.base.gestaomaquinas.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class DataInstalacaoTest {
    
    public DataInstalacaoTest() {
    }
    /**
     * Teste data instalacao nula
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarDataDeInstalacaoNaoNula() {
        System.out.println("Teste data instalacao nula");
        new DataInstalacao(null);
    }
    /**
     * Teste data instalacao vazia
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarDataDeInstalacaoNaoVazia() {
        System.out.println("Teste data instalacao vazia");
        new DataInstalacao("");
    }
    /**
     * Teste data instalacao formato errado
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarDataDeInstalacaoFormatoErrado() {
        System.out.println("Teste data instalacao formato errado");
        new DataInstalacao("2020/05/07");
    }
    /**
     * Teste data instalacao formato errado
     */
    @Test
    public void verificarDataDeInstalacaoFormatoValido() {
        System.out.println("Teste data instalacao formato errado");
        new DataInstalacao("07/05/2002");
    }
    /**
     * teste a uma de Teste data instalacao futura (inválida)
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarDataDeInstalacaoDataFutura() {
        System.out.println("Teste data instalacao futura");
        new DataInstalacao("07/05/2052");
    }
    
}
