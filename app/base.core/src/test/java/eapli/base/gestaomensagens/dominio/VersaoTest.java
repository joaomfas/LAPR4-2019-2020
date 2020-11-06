package eapli.base.gestaomensagens.dominio;

import org.junit.Test;
import static org.junit.Assert.*;

public class VersaoTest {
    
    public VersaoTest() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void verificarVersaoInvalida1() {
        System.out.println("verificarVersaoInvalida1");
        new Versao(-1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void verificarVersaoInvalida2() {
        System.out.println("verificarVersaoInvalida1");
        new Versao(256);
    }
    
    @Test
    public void verificarVersaoValida() {
        new Code(0);
    }
    
}
