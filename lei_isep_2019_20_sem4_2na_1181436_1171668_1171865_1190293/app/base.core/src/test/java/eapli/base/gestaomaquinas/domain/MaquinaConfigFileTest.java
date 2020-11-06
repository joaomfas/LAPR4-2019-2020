package eapli.base.gestaomaquinas.domain;

import org.junit.Test;
import eapli.base.gestaomaquinas.domain.MaquinaConfigFile;
import static org.junit.Assert.assertEquals;

public class MaquinaConfigFileTest {
    

    
    public MaquinaConfigFileTest() {
    }
 
    @Test(expected = IllegalArgumentException.class)
    public void verificarMaquinaConfigNula() {
        byte[] conteudoficheiro = "conteudo do ficheiro".getBytes();
        String desc = "descricao";
        System.out.println("Teste ficheiro nulo");
        new MaquinaConfigFile(null, desc);
    }
    
    
    @Test//(expected = IllegalArgumentException.class)
    public void verificarMaquinaConfigFileSemDescricao() {
        byte[] conteudoficheiro = "conteudo do ficheiro".getBytes();
        System.out.println("Teste sem Descricao");
        MaquinaConfigFile m = new MaquinaConfigFile(conteudoficheiro, "");
    }
    
    
    @Test(expected = IllegalArgumentException.class)
    public void verificarMaquinaConfigExcedeTamanhoMax() {
        byte[] conteudoficheiro = "conteudo do ficheiro".getBytes();
        String desc = "descricao";
        System.out.println("Teste descrição excede tamanho máximo (50+1)");
        new MaquinaConfigFile(conteudoficheiro, "123456789 123456789 123456789 123456789 123456789 1");
    }

    @Test
    public void verificaMaquinaConfigFile() {
        byte[] conteudoficheiro = "conteudo do ficheiro".getBytes();
        String desc = "descricao";
        MaquinaConfigFile expected = new MaquinaConfigFile(conteudoficheiro, desc);
        MaquinaConfigFile result = new MaquinaConfigFile(conteudoficheiro, desc);
        assertEquals(expected, result);
    }

}
