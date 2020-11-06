package eapli.base.gestaochaofabrica.export.application;

import eapli.base.gestaoordensproducao.application.ListarExecucoesOrdemProducaoService;
import eapli.base.gestaoordensproducao.domain.ExecucaoOrdemProducao;
import eapli.base.utils.ManipularFicheiroXML;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author mdias
 */
public class ExportExecucoesOrdemProducaoController {
    private final ListarExecucoesOrdemProducaoService listSvc = new ListarExecucoesOrdemProducaoService();
    private final ExecucaoOrdemProducaoExporterFactory factory = new ExecucaoOrdemProducaoExporterFactory();
    private final ExecucaoOrdemProducaoExporterService exportSvc = new ExecucaoOrdemProducaoExporterService();

    public void export(String filename, FileFormat format, Date dataI, Date dataF) throws IOException {
        final ChaoFabricaExporter exporter = factory.build(format);

        final Iterable<ExecucaoOrdemProducao> execucoesOrdemProducao = listSvc.allExecucoesOrdemProducao();
        exportSvc.export(execucoesOrdemProducao, filename, exporter, dataI, dataF);
        ManipularFicheiroXML.exportarDocumento(filename);
    }
    
}
