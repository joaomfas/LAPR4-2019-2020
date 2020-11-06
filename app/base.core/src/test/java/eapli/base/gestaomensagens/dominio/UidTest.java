package eapli.base.gestaomensagens.dominio;

import org.junit.Test;
import static org.junit.Assert.*;

public class UidTest {
    
    public UidTest() {
    }

    
    @Test(expected = IllegalArgumentException.class)
    public void verificarIDInvalido1() {
        System.out.println("verificarIdInvalida1");
        new Uid(-1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void verificarIDInvalido2() {
        System.out.println("verificarIdInvalida1");
        new Uid(65536);
    }
    
    @Test
    public void verificarIDValido() {
        new Uid(1777);
    }
    
    
}
