package eapli.base.infrastructure.bootstrapers;

import eapli.base.gestaoprodutos.application.RegistarProdutoController;
import eapli.base.gestaoprodutos.domain.CodigoFabrico;
import eapli.base.gestaoprodutos.domain.FichaProducao;
import eapli.base.gestaoprodutos.domain.Produto;
import eapli.base.gestaoprodutos.repositories.ProdutoRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ProdutosBootstrapperBase {
private static final Logger LOGGER = LoggerFactory.getLogger(UsersBootstrapperBase.class);

    final RegistarProdutoController registarProdutoController = new RegistarProdutoController();
    final ProdutoRepository prodRepo = PersistenceContext.repositories().produtos();

    public ProdutosBootstrapperBase() {
        super();
    }

    protected Produto registarProduto(final String codigoFabrico, final String codigoComercial, 
            final String descricaoBreve,final String descricaoCompleta, final String categoria, final String unMedida) {
        Produto u = null;
        try {
            u = registarProdutoController.registarProduto(codigoFabrico, codigoComercial, descricaoBreve,
                    descricaoCompleta, categoria, unMedida);
            LOGGER.debug("»»» Produto - Código fabrico: %s", codigoFabrico);
        } catch (final IntegrityViolationException | ConcurrencyException e) {

        }
        return u;
    }
    
    protected void registarFichaProducao(final String codigoFabrico, final FichaProducao ficha){
        Produto u = prodRepo.ofIdentity(new CodigoFabrico(codigoFabrico)).orElse(null);
        u.updateFichaProducao(ficha);
        prodRepo.save(u);
    }
}
