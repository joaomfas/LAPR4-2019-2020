package eapli.base.infrastructure.smoketests;

import eapli.base.infrastructure.smoketests.gestaoDeposito.GestaoDepositoSmokeTester;
import eapli.base.infrastructure.smoketests.gestaoMateriaPrima.GestaoMateriaPrimaSmokeTester;
import eapli.base.infrastructure.smoketests.gestaoProdutos.GestaoProdutosSmokeTester;
import eapli.base.infrastructure.smoketests.gestaochaofabrica.CategoriaMateriaPrimaExportSmokeTester;
import eapli.base.infrastructure.smoketests.gestaochaofabrica.ChaoFabricaExportSmokeTester;
import eapli.base.infrastructure.smoketests.gestaochaofabrica.DepositoExportSmokeTester;
import eapli.base.infrastructure.smoketests.gestaochaofabrica.ExecucaoOrdemProducaoExportSmokeTester;
import eapli.base.infrastructure.smoketests.gestaochaofabrica.LinhaProducaoExportSmokeTester;
import eapli.base.infrastructure.smoketests.gestaochaofabrica.MaquinaExportSmokeTester;
import eapli.base.infrastructure.smoketests.gestaochaofabrica.MateriaPrimaExportSmokeTester;
import eapli.base.infrastructure.smoketests.gestaochaofabrica.OrdemProducaoExportSmokeTester;
import eapli.base.infrastructure.smoketests.gestaochaofabrica.ProdutoExportSmokeTester;
import eapli.base.infrastructure.smoketests.gestaolinhaproducao.GestaoLinhaProducaoSmokeTester;
import eapli.base.infrastructure.smoketests.gestaomaquina.GestaoMaquinaSmokeTester;
import eapli.base.infrastructure.smoketests.gestaoordensproducao.GestaoOrdemProducaoSmokeTester;
import eapli.framework.actions.Action;

/**
 * execute simple smoke tests on the application layer. we are assuming that the
 * bootstrappers mainly test the "register" use cases and some of the "finders"
 * to support those "register", so this class will focus mainly on executing the
 * other application methods
 *
 */
@SuppressWarnings("squid:S1126")
public class BaseDemoSmokeTester implements Action {

    @Override
    public boolean execute() {
        new GestaoProdutosSmokeTester().execute();
        new GestaoLinhaProducaoSmokeTester().execute();
        new GestaoMaquinaSmokeTester().execute();
        new GestaoMateriaPrimaSmokeTester().execute();
        new GestaoDepositoSmokeTester().execute();
        new GestaoOrdemProducaoSmokeTester().execute();
        //testes a export XML
//        new DepositoExportSmokeTester().execute();
//        new CategoriaMateriaPrimaExportSmokeTester().execute();
//        new MateriaPrimaExportSmokeTester().execute();
//        new MaquinaExportSmokeTester().execute();
//        new LinhaProducaoExportSmokeTester().execute();
//        new ProdutoExportSmokeTester().execute();
//        new OrdemProducaoExportSmokeTester().execute();
//        new ExecucaoOrdemProducaoExportSmokeTester().execute();
          new ChaoFabricaExportSmokeTester().execute();
        return true;
    }
}
