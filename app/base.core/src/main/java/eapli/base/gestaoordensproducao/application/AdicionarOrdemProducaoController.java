package eapli.base.gestaoordensproducao.application;

import eapli.base.gestaoordensproducao.domain.OrdemProducao;
import eapli.base.gestaoordensproducao.domain.OrdemProducaoBuilder;
import eapli.base.gestaoordensproducao.repositories.OrdemProducaoRepository;
import eapli.base.gestaoprodutos.application.ListarProdutosService;
import eapli.base.gestaoprodutos.domain.Produto;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.Controller;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class AdicionarOrdemProducaoController implements Controller {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final OrdemProducaoRepository repository = PersistenceContext.repositories().ordensProducao();
    private final ListarProdutosService svcProdutos = new ListarProdutosService();
    private final OrdemProducaoBuilder builder = new OrdemProducaoBuilder();

    public OrdemProducao registarOrdemProducao(String numOrdemProducao, String dataEmissao, String dataPrevista,
            Produto produto, Double quantidade) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.GESTOR_PRODUCAO, BaseRoles.POWER_USER);
        builder.ordemProducaoDetails(numOrdemProducao, dataEmissao, dataPrevista, produto, quantidade);
        OrdemProducao ordemProducao = builder.build();        
        this.repository.save(ordemProducao);
        return ordemProducao;
    }

    public void adicionarEncomenda(String encomenda) {
        builder.adicionarEncomenda(encomenda);
    }

    public Iterable<Produto> allProdutos() {
        return this.svcProdutos.allProdutos();
    }
}
