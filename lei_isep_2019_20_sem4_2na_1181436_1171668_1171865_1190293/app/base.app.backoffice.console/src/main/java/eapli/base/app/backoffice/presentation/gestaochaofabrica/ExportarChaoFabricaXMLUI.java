package eapli.base.app.backoffice.presentation.gestaochaofabrica;

import eapli.base.app.backoffice.presentation.gestaochaofabrica.printer.InfoAConverterPrinter;
import eapli.base.app.backoffice.presentation.gestaochaofabrica.printer.InfoAExportarPrinter;
import eapli.base.gestaochaofabrica.export.application.ExportChaoFabricaController;
import eapli.base.gestaochaofabrica.export.application.InfoAExportar;
import eapli.base.gestaochaofabrica.export.application.OpcConversaoInfo;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ExportarChaoFabricaXMLUI extends AbstractUI {

    private final int EXIT_OPTION = 0;

    private ExportChaoFabricaController theController = new ExportChaoFabricaController();

    @Override
    protected boolean doShow() {

        final SelectWidget<InfoAExportar> selector = new SelectWidget<>("Informação a exportar:",
                Arrays.asList(InfoAExportar.values()),
                new InfoAExportarPrinter());

        final SelectWidget<OpcConversaoInfo> selectorConvercao = new SelectWidget<>(
                "Opções de conversão:",
                Arrays.asList(OpcConversaoInfo.values()),
                new InfoAConverterPrinter());

        selector.show();
        if (selector.selectedOption() == EXIT_OPTION) {
            return false;
        }

        selectorConvercao.show();
        if (selectorConvercao.selectedOption() == EXIT_OPTION) {
            return false;
        }

        final InfoAExportar info = selector.selectedElement();
        final OpcConversaoInfo infoOpcExtra  = selectorConvercao.selectedElement();

        String dataIStr = "01/01/1970";
        String dataFStr = "31/12/2100";
        
        String dataIStr1 = "";
        String dataFStr1 = "";
        
        if (info == InfoAExportar.TUDO) {
            dataIStr1 = Console.readLine("Data inicio (DD/MM/AAAA) [Dê ENTER caso não queria filtro temporal]:");         
            if(!dataIStr1.isEmpty()){
                dataFStr1 = Console.readLine("Data inicio (DD/MM/AAAA):");
            }
        }

        if(!dataIStr1.isEmpty()){
            dataIStr = dataIStr1;
            dataFStr = dataFStr1;
        }
        
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date dataI;
        Date dataF;
        try {
            dataI = format.parse(dataIStr);
            dataF = format.parse(dataFStr);
        } catch (ParseException ex) {
            System.out.println("Data inválida!");
            return false;
        }

        try {
            if (theController.export(System.getProperty("user.home") + "/xml/ChaoDeFabrica.xml", info, dataI, dataF, infoOpcExtra)) {
                System.out.println("Ficheiro exportado e validado com sucesso!");
            } else {
                System.out.println("Ficheiro exportado mas validação falhou!");
            }

            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    @Override
    public String headline() {
        return "Exportar dados para XML";
    }

}
