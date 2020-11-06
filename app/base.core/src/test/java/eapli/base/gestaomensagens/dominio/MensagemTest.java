package eapli.base.gestaomensagens.dominio;

import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

public class MensagemTest {
    
    public MensagemTest() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void vericarMensagemNaoNula() {
        new Mensagem(null, null, null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void verificarMensagemInvalida1() {
        String mensagem = "abc";
        new Mensagem(1, Codes.HELLO.getCode(), 1770, 1, mensagem);
    }
    
    @Test
    public void verificarMensagemValida() {
        Mensagem expected = new Mensagem("Mensagem válida", new Date(), new Date());
        Mensagem result = new Mensagem("Mensagem válida", new Date(), new Date());
        assertEquals(expected, result);
    }
    
    @Test
    public void verificarMensagemValida2() {
        String mensagem = "abc";
        new Mensagem(1, Codes.HELLO.getCode(), 1770, 3, mensagem);
    }
    
    
}
