package eapli.base.gestaomensagens.dominio;

import org.junit.Test;
import static org.junit.Assert.*;

public class CodeTest {
    
    public CodeTest() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void verificarCodigoInvalido1() {
        System.out.println("verificarCodigoInvalido1");
        new Code(-1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void verificarCodigoInvalido2() {
        System.out.println("verificarCodigoInvalido2");
        new Code(256);
    }
    
    @Test
    public void verificarCodigoValido() {
        System.out.println("verificarCodigoValido");
        new Code(0);
    }
    
    
    
}
