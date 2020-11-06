
package eapli.base.app.backoffice.presentation.materiasprimas;

import eapli.base.gestaomateriasprimas.domain.CategoriaMateriaPrima;
import eapli.framework.visitor.Visitor;

@SuppressWarnings("squid:S106")
public class CategoriaMateriaPrimaPrinter implements Visitor<CategoriaMateriaPrima> {

    @Override
    public void visit(final CategoriaMateriaPrima visitee) {
        System.out.printf("%-10s%-30s", visitee.identity(), visitee.descricao());
    }
}
