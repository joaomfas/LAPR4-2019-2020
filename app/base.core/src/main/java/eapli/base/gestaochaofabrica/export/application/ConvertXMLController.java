package eapli.base.gestaochaofabrica.export.application;

import java.io.IOException;


public class ConvertXMLController {

  private final ChaoDeFabricaConverterFactory factory = new ChaoDeFabricaConverterFactory();

  boolean checkExtraOpcaoConversao(OpcConversaoInfo opcConversao,String xmlFileName) {

      final String filesPath = System.getProperty("user.home")+"/xml/";
      final String baseFileName = "ChaoDeFabrica.";

      String xslFileName;
      String outputFileName;
      FileFormat format;

      switch (opcConversao){
            case EXPOTAR_XML_CONV_HTML:
                format = FileFormat.HTML;
                xslFileName = filesPath+format.getValue()+"_"+baseFileName+FileFormat.XSL;
                break;
            case EXPOTAR_XML_CONV_JSON:
                format = FileFormat.JSON;
                xslFileName = filesPath+format.getValue()+"_"+baseFileName+FileFormat.XSL;
                break;
            case EXPOTAR_XML_CONV_TXT:
                format = FileFormat.TXT;
                xslFileName = filesPath+format.getValue()+"_"+baseFileName+FileFormat.XSL;
                break;
            default:
                return false;
        }

        outputFileName = filesPath+baseFileName+format;
      return convert(format, xslFileName, xmlFileName, outputFileName);
    }

    public boolean convert(FileFormat format, String xslFileName, String xmlFileName, String outputFileName){

      Boolean isConversionSuccess = true;

        final ChaoFabricaConverter converter = factory.build(format);

        try {
            isConversionSuccess =  converter.convert(xslFileName, xmlFileName, outputFileName);
        } catch (IOException e) {
            isConversionSuccess = false;
            e.printStackTrace();
        } finally {
            return isConversionSuccess;
        }
    }

}
