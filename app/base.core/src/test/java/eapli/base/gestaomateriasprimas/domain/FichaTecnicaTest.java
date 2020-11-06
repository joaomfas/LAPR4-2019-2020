package eapli.base.gestaomateriasprimas.domain;

import java.io.FileNotFoundException;
import org.junit.Test;

/**
 *
 * @author mdias
 */
public class FichaTecnicaTest {

    /**
     * Teste carregar ficheiro válido
     */
    @Test
    public void lerNomeFicheiro() throws FileNotFoundException{
        System.out.println("Ficheiro de ficha técnica válido");
        FichaTecnica teste = new FichaTecnica(System.getProperty("user.dir") + "/src/main/resources/ficheirosTeste/ft_cortica.pdf");
        assert(teste!=null);
    } 
    
    /**
     * Teste sem nome de ficheiro de ficha técnica
     */
    @Test(expected = NullPointerException.class)
    public void verificarNomeFicheiroNaoVazio() throws FileNotFoundException {
        System.out.println("Teste nome de ficheiro de ficha técnica vazio");
        new FichaTecnica(null);
    }
    
    /**
     * Teste com nome de ficheiro não existente de ficha técnica
     */
    @Test(expected = FileNotFoundException.class)
    public void verificarNomeFicheiroNaoExistente() throws FileNotFoundException {
        System.out.println("Teste nome de ficheiro de ficha técnica inexistente");
        new FichaTecnica(System.getProperty("user.dir") + "/src/main/resources/ficheirosTeste/xpto.pdf");
    }

}
