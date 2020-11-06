package eapli.base.gestaomateriasprimas.domain;

import eapli.base.gestaomaquinas.domain.Descricao;
import eapli.base.gestaoprodutos.domain.UnidadeMedida;
import java.io.FileNotFoundException;
import org.junit.Test;

/**
 *
 * @author mdias
 */
public class MateriaPrimaTest {

    FichaTecnica fichaTecnica;
    CategoriaMateriaPrima catMP = new CategoriaMateriaPrima(new CodigoCategoria("123"), new Descricao("descrição"));

    @Test
    public void lerFichaTecnica() throws FileNotFoundException {
        fichaTecnica = new FichaTecnica(System.getProperty("user.dir") + "/src/main/resources/ficheirosTeste/ft_cortica.pdf");
    }

    /**
     * Teste sem código interno de matéria prima
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarCodInternoNaoVazio() {
        System.out.println("Teste código interno de matéria prima vazio");
        new MateriaPrima(null, catMP, new Descricao("descrição"), fichaTecnica, new UnidadeMedida("kg"));
    }

    /**
     * Teste sem categoria de matéria prima
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarCatMateriaPrimaNaoVazia() {
        System.out.println("Teste categoria de matéria prima vazia");
        new MateriaPrima(new CodigoInterno("codigoInterno"), null, new Descricao("descrição"), fichaTecnica, new UnidadeMedida("kg"));
    }

    /**
     * Teste sem descrição de matéria prima
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarDescMateriaPrimaNaoVazia() {
        System.out.println("Teste descrição de matéria prima vazia");
        new MateriaPrima(new CodigoInterno("codigoInterno"), catMP, null, fichaTecnica, new UnidadeMedida("kg"));
    }

    /**
     * Teste sem ficha técnica de matéria prima
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarFichaTécnicaMateriaPrimaNaoVazia() {
        System.out.println("Teste ficha técnica de matéria prima vazia");
        new MateriaPrima(new CodigoInterno("codigoInterno"), catMP, new Descricao("descrição"), null, new UnidadeMedida("kg"));
    }

    /**
     * Teste sem unidade de medida de matéria prima
     */
    @Test(expected = IllegalArgumentException.class)
    public void verificarUnidadeMedidaMateriaPrimaNaoVazia() {
        System.out.println("Teste unidade de medida de matéria prima vazia");
        new MateriaPrima(new CodigoInterno("codigoInterno"), catMP, new Descricao("descrição"), fichaTecnica, null);
    }

}
