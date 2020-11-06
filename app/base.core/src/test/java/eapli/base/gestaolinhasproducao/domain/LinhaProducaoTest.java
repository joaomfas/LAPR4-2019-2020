package eapli.base.gestaolinhasproducao.domain;

import eapli.base.gestaomaquinas.domain.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class LinhaProducaoTest {

    
    public LinhaProducaoTest() {
        
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void verificarLinhaProducaoNaoNula() {
        System.out.println("Linha Producao nula");
        new LinhaProducao(null);
    }
    
    @Test
    public void verificarLinhaProducaoValida() {
        System.out.println("Linha Producao Válida");
        new LinhaProducao("Seq10");
    }
    
}
