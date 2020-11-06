/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.gestaochaofabrica.export.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author mdias
 */
public class XMLConverterService implements ChaoFabricaConverter {
    
    private static final Logger logger = LogManager.getLogger(XMLConverterService.class);

    public boolean convert(String xslFileName, String xmlFileName, String outputFileName) {
        Boolean isConversionSuccess = true;
        try {

            TransformerFactory tranformerFactory = TransformerFactory.newInstance();
            Transformer transformer = tranformerFactory.newTransformer(new javax.xml.transform.stream.StreamSource(xslFileName));
            transformer.transform(
                new javax.xml.transform.stream.StreamSource(xmlFileName),
                new javax.xml.transform.stream.StreamResult(new FileOutputStream(outputFileName))
            );
        } catch (IOException e) {
            logger.error("Falha ao converter para HTML", e);
            isConversionSuccess = false;
            throw e;
        } finally {
           return isConversionSuccess;
        }
    }

}
