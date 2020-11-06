package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.gestaodepositos.domain.CodigoDeposito;
import eapli.base.gestaodepositos.domain.Deposito;
import eapli.base.gestaomaquinas.domain.Descricao;
import eapli.base.gestaoordensproducao.domain.CodigoOrdemProducao;
import eapli.base.gestaoordensproducao.domain.DataEmissao;
import eapli.base.gestaoordensproducao.domain.DataPrevista;
import eapli.base.gestaoordensproducao.domain.Encomenda;
import eapli.base.gestaoordensproducao.domain.ErroProcessamento;
import eapli.base.gestaoordensproducao.domain.OrdemProducao;
import eapli.base.gestaoprodutos.domain.CodigoFabrico;
import eapli.base.gestaoprodutos.domain.Produto;
import eapli.base.gestaoprodutos.repositories.ProdutoRepository;
import eapli.framework.actions.Action;
import eapli.base.infrastructure.bootstrapers.ErroProcessamentoArquivadoBase;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.spm.application.processamento.validacao.exceptions.EnumErros;
import java.util.HashSet;
import java.util.Set;

public class ErroProcArquivadoBootstrapper extends ErroProcessamentoArquivadoBase implements Action{

    ProdutoRepository prodRepo = PersistenceContext.repositories().produtos();
    
    @Override
    public boolean execute() {
        registarErroArquivado(new ErroProcessamento(EnumErros.SPM, null));
        registarErroArquivado(new ErroProcessamento(EnumErros.DATA_INVALIDA, null));
        
        Set<Encomenda> listaEnc = new HashSet<>();
        listaEnc.add(new Encomenda("98768231"));
        
        Produto p = prodRepo.ofIdentity(new CodigoFabrico("50000106")).orElseThrow(IllegalStateException::new);
        
        registarOrdemProducao(new OrdemProducao(new CodigoOrdemProducao("100003363"), new DataEmissao("28/05/2020"), p, 65000D, new DataPrevista("01/07/2020"), listaEnc));
        
        return true;
    }

}
