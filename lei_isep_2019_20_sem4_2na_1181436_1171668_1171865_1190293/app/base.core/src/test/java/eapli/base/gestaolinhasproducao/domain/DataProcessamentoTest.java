package eapli.base.gestaolinhasproducao.domain;

import java.util.Calendar;
import org.junit.Test;

/**
 *
 * @author mdias
 */
public class DataProcessamentoTest {

    /**
     * Teste para formato de data inválido
     */
    @Test
    public void verificarDataInvalida() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1993);
        cal.set(Calendar.MONTH, 5);
        cal.set(Calendar.DAY_OF_MONTH, 7);

        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.YEAR, 2007);
        cal1.set(Calendar.MONTH, 5);
        cal1.set(Calendar.DAY_OF_MONTH, 7);
        System.out.println("Teste formato de data inválido");
        DataProcessamento result = new DataProcessamento(cal.getTime());
        DataProcessamento expected = new DataProcessamento(cal1.getTime());
        assert (result != expected);
    }

}
