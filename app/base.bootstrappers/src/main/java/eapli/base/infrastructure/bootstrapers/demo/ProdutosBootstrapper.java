package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.gestaomateriasprimas.domain.CodigoInterno;
import eapli.base.gestaomateriasprimas.domain.MateriaPrima;
import eapli.base.gestaomateriasprimas.repositories.MateriaPrimaRepository;
import eapli.base.gestaoprodutos.domain.Componente;
import eapli.base.gestaoprodutos.domain.FichaProducao;
import eapli.base.infrastructure.bootstrapers.ProdutosBootstrapperBase;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.actions.Action;

public class ProdutosBootstrapper extends ProdutosBootstrapperBase implements Action {

    MateriaPrimaRepository mpRepo = PersistenceContext.repositories().materiasPrimas();
    
    @Override
    public boolean execute() {
        registarProduto("3001", "10001", "Mesa pequena", "Mesa 1pax", "Mesa", "un");
        registarProduto("3002", "10002", "Mesa média", "Mesa 4pax", "Mesa", "un");
        registarProduto("3003", "10003", "Mesa grande", "Mesa 6pax", "Mesa", "un");
        registarProduto("3004", "10004", "Cadeira A", "Cadeira s/almofada", "Cadeira", "un");
        registarProduto("3005", "10005", "Cadeira B", "Cadeira almofadada", "Cadeira", "un");
        registarProduto("3006", "10006", "Armário TV grande", "Armário para TV c/ 2 gavetas", "Armário", "un");
        registarProduto("3007", "10007", "Armário TV pequeno", "Armário para TV c/ 1 gavetas", "Armário", "un");
        registarProduto("50000106", "10008", "Produto A", "Produto A teste", "Produto", "un");

        FichaProducao fichaP = new FichaProducao();
        fichaP.addComponente(new Componente(getMP("32000142"), 0.0244));
        registarFichaProducao("50000106", fichaP);
        
        return true;
    }
    
    private MateriaPrima getMP(final String codigo) {
        return mpRepo.ofIdentity(new CodigoInterno(codigo)).orElseThrow(IllegalStateException::new);
    }
}
