
package eapli.base.gestaolinhasproducao.domain;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mdias
 */
public class EstadoProcessamentoRecorrenteTest {
    
    @Test(expected = AssertionError.class)
    public void verificarEstadoNaoNulo() {
        System.out.println("Estado de processamento nulo");
        assertEquals(null, EstadoProcessamentoRecorrente.ATIVO);  
    }
    
}
