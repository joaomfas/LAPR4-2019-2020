package eapli.base.gestaodepositos.domain;

import eapli.base.gestaomaquinas.domain.Descricao;
import org.junit.Test;

/**
 *
 * @author mdias
 */
public class DepositoTest {

    /**
     * Teste sem código de depósito
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarCodDepositoNaoVazio() {
        System.out.println("Teste código de depósito vazio");
        new Deposito(null, new Descricao("deposito1"));
    }

    /**
     * Teste sem descrição
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarDescricaoNaoVazia() {
        System.out.println("Teste descrição vazia");
        new Deposito(new CodigoDeposito("123"), null);
    }

}
