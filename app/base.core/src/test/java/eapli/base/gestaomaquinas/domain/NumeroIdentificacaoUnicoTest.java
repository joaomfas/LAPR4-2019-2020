package eapli.base.gestaomaquinas.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class NumeroIdentificacaoUnicoTest {
    
    public NumeroIdentificacaoUnicoTest() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void verificaNumeroIdentificacaoUnicaLimite1() {
        System.out.println("Teste numero unico limite 1 - 1");
        new NumeroIdentificacaoUnico("0");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void verificaNumeroIdentificacaoUnicaLimite2() {
        System.out.println("Teste numero unico limite 65535 + 1");
        new NumeroIdentificacaoUnico("65536");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void verificaNumeroIdentificacaoUnicaInvalido1() {
        System.out.println("Teste numero unico invalido : 10ABC");
        new NumeroIdentificacaoUnico("10ABC");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void verificaNumeroIdentificacaoUnicaVazio() {
        System.out.println("Teste numero unico vazio");
        new NumeroIdentificacaoUnico("");
    }
    
    @Test
    public void verificaNumeroIdentificacaoUnicoValido() {
        System.out.println("Teste numero unico válido");
        NumeroIdentificacaoUnico expected = new NumeroIdentificacaoUnico("10");
        NumeroIdentificacaoUnico result = new NumeroIdentificacaoUnico("10");
        assertEquals(expected, result);
    }
    
    @Test
    public void verificaNumeroIdentificacaoUnico2valido() {
        System.out.println("Teste numero unico válido");
        NumeroIdentificacaoUnico expected = new NumeroIdentificacaoUnico("10");
        NumeroIdentificacaoUnico result = new NumeroIdentificacaoUnico("30");
        
        assert(!expected.equals(result));
    }
    
    
}
