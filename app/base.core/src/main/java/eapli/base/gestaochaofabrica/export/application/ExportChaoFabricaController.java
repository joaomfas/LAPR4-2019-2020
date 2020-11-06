package eapli.base.gestaochaofabrica.export.application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

/**
 *
 * @author mdias
 */
public class ExportChaoFabricaController {

    final ExportCategoriasMateriaPrimaController exportCategoriasMateriaPrimasController = new ExportCategoriasMateriaPrimaController();
    final ExportMateriasPrimasController exportMateriasPrimasController = new ExportMateriasPrimasController();
    final ExportProdutosController exportProdutosController = new ExportProdutosController();
    final ExportDepositosController exportDepositosController = new ExportDepositosController();
    final ExportMaquinasController exportMaquinasController = new ExportMaquinasController();
    final ExportLinhasProducaoController exportLinhasProducaoController = new ExportLinhasProducaoController();
    final ExportOrdensProducaoController exportOrdensProducaoController = new ExportOrdensProducaoController();
    final ExportExecucoesOrdemProducaoController exportExecucoesOrdemProducaoController = new ExportExecucoesOrdemProducaoController();
    final ConvertXMLController convertXMLController = new ConvertXMLController();

    public boolean export(String filename, InfoAExportar info, Date dataI, Date dataF, OpcConversaoInfo opcConversao) throws IOException {

        Boolean isExportSuccess = exportAll(filename, info, dataI, dataF);

        if(isExportSuccess && !opcConversao.equals(OpcConversaoInfo.EXPORTAR_XML)){
            String xmlFileName = System.getProperty("user.home") + "/xml/ChaoDeFabrica.xml";
            if(convertXMLController.checkExtraOpcaoConversao(opcConversao, xmlFileName)){
                System.out.println("Conversão de XML realizada com sucesso.");
            } else{
                System.out.println("Erro ao tentar converter ficheiro XML.");
            }
        }

        return isExportSuccess;
    }

    private boolean exportAll(String filename, InfoAExportar info, Date dataI, Date dataF) throws IOException {
        final String filenameChaoFabrica = filename;
        ArrayList<String> files = new ArrayList<>();

        if (info.equals(InfoAExportar.PROD_MP) || info.equals(InfoAExportar.TUDO)) {
            final String filenameCatMP = System.getProperty("user.home") + "/xml/Categorias." + FileFormat.XML;
            final String filenameMP = System.getProperty("user.home") + "/xml/MateriasPrimas." + FileFormat.XML;
            final String filenameP = System.getProperty("user.home") + "/xml/Produtos." + FileFormat.XML;
            files.add(filenameCatMP);
            files.add(filenameMP);
            files.add(filenameP);
            exportCategoriasMateriaPrimasController.export(filenameCatMP, FileFormat.XML);
            exportMateriasPrimasController.export(filenameMP, FileFormat.XML);
            exportProdutosController.export(filenameP, FileFormat.XML);
        }

        if (info.equals(InfoAExportar.LINHA_MAQ) || info.equals(InfoAExportar.TUDO)) {
            final String filenameD = System.getProperty("user.home") + "/xml/Depositos." + FileFormat.XML;
            final String filenameM = System.getProperty("user.home") + "/xml/Maquinas." + FileFormat.XML;
            final String filenameL = System.getProperty("user.home") + "/xml/LinhasProducao." + FileFormat.XML;
            files.add(filenameD);
            files.add(filenameM);
            files.add(filenameL);
            exportDepositosController.export(filenameD, FileFormat.XML);
            exportMaquinasController.export(filenameM, FileFormat.XML);
            exportLinhasProducaoController.export(filenameL, FileFormat.XML);
        }

        if (info.equals(InfoAExportar.TUDO)) {
            final String filenameO = System.getProperty("user.home") + "/xml/OrdensProducao." + FileFormat.XML;
            final String filenameE = System.getProperty("user.home") + "/xml/Execucoes." + FileFormat.XML;
            files.add(filenameO);
            files.add(filenameE);
            exportOrdensProducaoController.export(filenameO, FileFormat.XML);
            exportExecucoesOrdemProducaoController.export(filenameE, FileFormat.XML, dataI, dataF);
        }

        try {
            PrintWriter pw = new PrintWriter(filenameChaoFabrica, "UTF-8");

            pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            pw.println("<ChaoDeFabrica xmlns:xs=\"http://www.w3.org/2001/XMLSchema\">");

            for (String file : files) {
                File f = new File(file);

                BufferedReader br = new BufferedReader(new FileReader(f));

                String line = br.readLine();
                while (line != null) {
                    pw.println(line);
                    line = br.readLine();
                }
                pw.flush();
                br.close();
            }

            pw.println("</ChaoDeFabrica>");
            pw.close();

            return validarXML(filenameChaoFabrica, System.getProperty("user.home") + "/xml/ChaoDeFabrica.xsd");

        } catch (IOException ex) {
            throw new IOException(ex.getMessage());
        }
    }

    private boolean validarXML(String xmlFile, String schemaFile) {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(new File(schemaFile));

            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlFile)));
            return true;
        } catch (SAXException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
