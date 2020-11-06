package eapli.base.gestaomensagens.application;

import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import eapli.base.gestaomaquinas.application.ListarLinhaProducaoService;
import eapli.framework.application.Controller;

public class SCMServerController implements Controller {

    private CarregarMensagemFactory factory = new CarregarMensagemFactory();
    private ListarLinhaProducaoService svc = new ListarLinhaProducaoService();

    public Iterable<LinhaProducao> linhasProducao() {
        return svc.todasLinhas();
    }

    public void startSCMServer() throws Exception {
        for (LinhaProducao linha : linhasProducao()) {
            ICarregamentoMensagemImporter r = factory.build(ModoCarregamentoMensagens.SERVIDOR);
            r.receiveParameter(linha);
            new Thread((Runnable) r).start();
        }
    }
}
