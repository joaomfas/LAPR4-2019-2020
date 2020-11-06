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
public enum OpcConversaoInfo {
    EXPORTAR_XML("Exportação simples para XML"),
    EXPOTAR_XML_CONV_HTML("Exportação para XML e conversão para HTML"),
    EXPOTAR_XML_CONV_JSON("Exportação para XML e conversão para JSON"),
    EXPOTAR_XML_CONV_TXT("Exportação para XML e conversão para TXT");


    private String desc;

    public String getValue(){
        return this.desc;
    }

    private OpcConversaoInfo(String desc){
        this.desc = desc; 
    } 
}
