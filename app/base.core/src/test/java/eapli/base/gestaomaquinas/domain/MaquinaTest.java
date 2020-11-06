package eapli.base.gestaomaquinas.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class MaquinaTest {
    
    private CodInterno codInterno;
    
    private Descricao descricao;
    
    private NumSerie numserie;
    
    private DataInstalacao datainstalacao;
    
    private Marca marca;
    
    private Modelo modelo;

    private NumeroIdentificacaoUnico niu;
        
    public MaquinaTest() {
        codInterno = new CodInterno("ABC");
        descricao = new Descricao("ABC");
        numserie = new NumSerie("ABC");
        datainstalacao = new DataInstalacao("10/1/2020");
        marca = new Marca("ABC");
        modelo = new Modelo("ABC");
        niu = new NumeroIdentificacaoUnico("77");
    }
    /**
     * codInterno null
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarCodFabricoNaoVazio() {
        System.out.println("Teste codInterno null");
        new Maquina(null, descricao, numserie, datainstalacao, marca, modelo, niu);
    }
    
    
    /**
     * descricao null
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarDescricaoNaoVazio() {
        System.out.println("Teste descricao null");
        new Maquina(codInterno, null, numserie, datainstalacao, marca, modelo, niu);
    }
    
    /**
     * numserie null
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarNumserieNaoVazio() {
        System.out.println("Teste numserie null");
        new Maquina(codInterno, descricao, null, datainstalacao, marca, modelo, niu);
    }
    
    /**
     * datainstalacao null
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarDatainstalacaoNaoVazio() {
        System.out.println("Teste datainstalacao null");
        new Maquina(codInterno, descricao, numserie, null, marca, modelo, niu);
    }
    
    /**
     * marca null
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarMarcaNaoVazio() {
        System.out.println("Teste marca null");
        new Maquina(codInterno, descricao, numserie, datainstalacao, null, modelo, niu);
    }
    
    /**
     * modelo null
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarModeloNaoVazio() {
        System.out.println("Teste modelo null");
        new Maquina(codInterno, descricao, numserie, datainstalacao, marca, null, niu);
    }
    
    /**
     * numIdUnico null
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarNumeroIdUnicoNaoVazio() {
        System.out.println("Teste codInterno null");
        new Maquina(codInterno, descricao, numserie, datainstalacao, marca, modelo, null);
    }
    
}
