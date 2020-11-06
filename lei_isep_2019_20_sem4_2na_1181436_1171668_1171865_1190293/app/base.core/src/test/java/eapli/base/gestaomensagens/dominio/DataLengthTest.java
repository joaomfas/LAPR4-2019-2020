package eapli.base.gestaomensagens.dominio;

import org.junit.Test;

public class DataLengthTest {
    
    public DataLengthTest() {
    }

    @Test
    public void verificarDataLengthInvalida1() {
        System.out.println("verificarDataLengthInvalida1");
        new DataLength(0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void verificarDataLengthInvalida2() {
        System.out.println("verificarDataLengthInvalida2");
        new DataLength(65536);
    }
    
    @Test
    public void verificarDataLengthValida() {
        System.out.println("verificarDataLengthValida");
        new DataLength(300);
    }
    
    
    
    
}
