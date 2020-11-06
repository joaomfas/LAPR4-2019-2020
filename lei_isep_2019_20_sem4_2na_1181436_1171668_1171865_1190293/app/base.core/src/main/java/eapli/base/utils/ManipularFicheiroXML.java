package eapli.base.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author mdias
 */
public class ManipularFicheiroXML {

    public static Document exportarDocumento(String filenamePath) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filenamePath), "UTF-8"));
            InputSource is = new InputSource(reader);
            is.setEncoding("UTF-8");
            Document doc = builder.parse(is);
            return doc;
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(ManipularFicheiroXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
