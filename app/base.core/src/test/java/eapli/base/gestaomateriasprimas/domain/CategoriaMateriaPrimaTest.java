package eapli.base.gestaomateriasprimas.domain;

import eapli.base.gestaomaquinas.domain.Descricao;
import org.junit.Test;

/**
 *
 * @author mdias
 */
public class CategoriaMateriaPrimaTest {

    /**
     * Teste sem código de categoria
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarCodCategoriaNaoVazio() {
        System.out.println("Teste código de categoria vazio");
        new CategoriaMateriaPrima(null, new Descricao("descrição"));
    }
    
    /**
     * Teste sem descrição
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarDescricaoNaoVazia() {
        System.out.println("Teste descrição vazia");
        new CategoriaMateriaPrima(new CodigoCategoria("123"), null);
    }
    
}
