/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.gestaolinhasproducao.domain;

import eapli.base.gestaomaquinas.domain.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 35193
 */
public class SequenciaTest {

    private Long numSquenciaMaquina;
    
    private Maquina maquina;
    
    private CodInterno codInterno;
    
    private Descricao descricao;
    
    private NumSerie numserie;
    
    private DataInstalacao datainstalacao;
    
    private Marca marca;
    
    private Modelo modelo;
    
    private NumeroIdentificacaoUnico niu;
    
    public SequenciaTest() {
        codInterno = new CodInterno("ABC");
        descricao = new Descricao("ABC");
        numserie = new NumSerie("ABC");
        datainstalacao = new DataInstalacao("10/1/2020");
        marca = new Marca("ABC");
        modelo = new Modelo("ABC");
        niu = new NumeroIdentificacaoUnico("78");
        maquina = new Maquina(codInterno, descricao, numserie, datainstalacao, marca, modelo, niu);
        numSquenciaMaquina = 10l;
    }
    /**
     * codInterno null
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarMaquinaNaoVazio() {
        System.out.println("Teste Sequencia inválida: maquina null");
        new Sequencia(numSquenciaMaquina, null);
    }

    
    /**
     * sequencia negativa
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarSequenciaNegativa() {
        System.out.println("Teste Sequencia inválida: nr sequencia");
        new Sequencia(-2l, maquina);
    }
    
    /**
     * sequencia inválida
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarSequenciaNaoNula() {
        System.out.println("Teste Sequencia inválida: nr sequencia");
        new Sequencia(0l, maquina);
    }
    
    /**
     * sequencia válida
     */
    @Test
    public void verificarSequencia() {
        System.out.println("Teste Sequencia válida");
        new Sequencia(numSquenciaMaquina, maquina);
    }
}
