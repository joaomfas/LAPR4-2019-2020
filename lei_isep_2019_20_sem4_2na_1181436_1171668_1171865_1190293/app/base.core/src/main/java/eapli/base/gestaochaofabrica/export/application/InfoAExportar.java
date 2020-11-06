/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.gestaochaofabrica.export.application;

/**
 *
 * @author joaoferreira
 */
public enum InfoAExportar {
    PROD_MP("Produtos e Matérias Primas"), 
    LINHA_MAQ("Linhas de Produção e Máquinas"), 
    TUDO("Toda a informação"); 
  
    private String desc; 
  
    public String getValue(){ 
        return this.desc; 
    } 
  
    InfoAExportar(String desc){
        this.desc = desc; 
    }
}
