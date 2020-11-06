package eapli.base.gestaoprodutos.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import java.util.Objects;
import javax.measure.Unit;
import javax.measure.format.ParserException;
import javax.measure.quantity.Dimensionless;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import tec.units.ri.format.SimpleUnitFormat;
import tec.units.ri.quantity.Quantities;

@Embeddable
public class UnidadeMedida implements ValueObject {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private final String unidadeMedida;

    public UnidadeMedida(final String unidadeMedida) {
        verificaRequisitosUnidadeMedida(unidadeMedida);
        this.unidadeMedida = unidadeMedida.toLowerCase();
    }

    private void verificaRequisitosUnidadeMedida(String unidadeMedida) {
        Preconditions.ensure(!unidadeMedida.isEmpty(), "Unidade de media vazia!");
        try {
            if (!unidadeMedida.equalsIgnoreCase("un")) {
                Quantities.getQuantity(1, SimpleUnitFormat.getInstance().parse(unidadeMedida)).getUnit();
            }
        } catch (ParserException e) {
            throw new IllegalArgumentException("Unidade de medida inválida!");
        }
    }

    protected UnidadeMedida() {
        this.unidadeMedida = null;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.unidadeMedida);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UnidadeMedida other = (UnidadeMedida) obj;
        return Objects.equals(this.unidadeMedida, other.unidadeMedida);
    }

    @Override
    public String toString() {
        return unidadeMedida;
    }
    public Unit unidadeMedida() {
        if (unidadeMedida.equalsIgnoreCase("UN")) {
            return Quantities.getQuantity("1").asType(Dimensionless.class).getUnit();
        } else {
            return Quantities.getQuantity(1, SimpleUnitFormat.getInstance().parse(unidadeMedida)).getUnit();
        }
    }
}
