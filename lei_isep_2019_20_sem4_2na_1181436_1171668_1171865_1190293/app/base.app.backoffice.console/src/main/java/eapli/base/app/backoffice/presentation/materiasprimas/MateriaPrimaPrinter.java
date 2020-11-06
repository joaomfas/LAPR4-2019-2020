package eapli.base.app.backoffice.presentation.materiasprimas;

import eapli.base.gestaomateriasprimas.domain.MateriaPrima;
import eapli.framework.visitor.Visitor;


@SuppressWarnings("squid:S106")
public class MateriaPrimaPrinter implements Visitor<MateriaPrima> {

    @Override
    public void visit(MateriaPrima visitee) {
        System.out.printf("%-25s%-25s%-30s%-10s", visitee.identity(), visitee.descricao(),
                visitee.categoriaMP().descricao(), visitee.unidadeMedida());
    }
}