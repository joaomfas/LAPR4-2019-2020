package eapli.base.gestaoprodutos.domain;

import eapli.base.gestaomateriasprimas.domain.MateriaPrima;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class Componente implements ValueObject {

    private static final long serialVersionUID = 1L;

    @ManyToOne()
    private final Produto itemProduto;
    @ManyToOne()
    private final MateriaPrima itemMP;
    private final Double quantidade;

    public Componente(final Produto itemProduto, final Double quantidade) {
        requisitosComponenteProduto(itemProduto, quantidade);
        this.itemProduto = itemProduto;
        this.itemMP = null;
        this.quantidade = quantidade;
    }

    private void requisitosComponenteProduto(Produto itemProduto, Double quantidade) {
        Preconditions.nonNull(itemProduto, "Item não pode ser null!");
        Preconditions.ensure(quantidade > 0, "Quantide não pode ser negativa!");
    }

    public Componente(final MateriaPrima itemMP, final Double quantidade) {
        requisitosComponenteMateriaPrima(itemMP, quantidade);
        this.itemProduto = null;
        this.itemMP = itemMP;
        this.quantidade = quantidade;
    }

    private void requisitosComponenteMateriaPrima(MateriaPrima itemMP, Double quantidade) {
        Preconditions.nonNull(itemMP, "Item não pode ser null!");
        Preconditions.ensure(quantidade > 0, "Quantide não pode ser negativa!");
    }

    protected Componente() {
        this.itemProduto = null;
        this.itemMP = null;
        this.quantidade = null;
    }

    public Produto produto() {
        return this.itemProduto;
    }

    public MateriaPrima materiaPrima() {
        return this.itemMP;
    }

    public Double quantidade() {
        return this.quantidade;
    }

    public boolean isProduto() {
        return this.itemProduto != null;
    }
}
