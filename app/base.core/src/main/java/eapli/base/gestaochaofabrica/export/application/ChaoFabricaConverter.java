/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.gestaochaofabrica.export.application;

import java.io.IOException;
import java.util.Date;


/**
 * Padrão Strategy
 * @autho thiagoff
 */
public interface ChaoFabricaConverter {

    boolean convert(String xslFileName, String xmlFileName, String outputFileName) throws IOException;

}
