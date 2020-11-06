package eapli.base.gestaoprodutos.application;

import eapli.base.gestaomateriasprimas.application.ListarMateriasPrimasService;
import eapli.base.gestaomateriasprimas.domain.MateriaPrima;
import eapli.base.gestaoprodutos.domain.FichaProducao;
import eapli.base.gestaoprodutos.domain.FichaProducaoBuilder;
import eapli.base.gestaoprodutos.domain.Produto;
import eapli.base.gestaoprodutos.repositories.ProdutoRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.Controller;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import java.util.Optional;


public class RegistarFichaProducaoController implements Controller{
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ProdutoRepository repository = PersistenceContext.repositories().produtos();
    private final FichaProducaoBuilder builder = new FichaProducaoBuilder();
    private final ListarProdutosService svcProdutos = new ListarProdutosService();
    private final ListarMateriasPrimasService svcMP = new ListarMateriasPrimasService();

    public FichaProducao registarFichaProducao(Produto produto) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.GESTOR_PRODUCAO, BaseRoles.POWER_USER);

        try {
            FichaProducao fichaProducao = builder.build();
            produto.updateFichaProducao(fichaProducao);
            this.repository.save(produto);
            
            Optional<Produto> prod = this.repository.ofIdentity(produto.identity());
            return fichaProducao;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public void adicionarProduto(Produto produto, Double quantidade){
        builder.adicionarProduto(produto, quantidade);
    }
    
    public void adicionarMateriaPrima(MateriaPrima materiaPrima, Double quantidade){
        builder.adicionarMateriaPrima(materiaPrima, quantidade);
    }
    
    public Iterable<Produto> allProdutos() {
        return this.svcProdutos.allProdutos();
    }
    
    public Iterable<MateriaPrima> allMateriasPrimas(){
        return this.svcMP.allMateriasPrimas();
    }
}
