package eapli.base.gestaoordensproducao.domain;

import eapli.base.gestaoprodutos.domain.Produto;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;

@Embeddable
public class Lote implements ValueObject{

    @OneToOne
    private final Produto produto;
    
    private final String codigo;

    private double quantidade;

    public Lote() {
        codigo = "";
        produto = null;
        quantidade = 0;
    }
    
    public Lote(String codigo, Produto produto) {
        Preconditions.nonEmpty(codigo, "Codigo lote não pode estar vazio!");
        this.produto = produto;
        this.codigo = codigo;
    }
    
    public void adicionarQuantidade(Double quantidade) {
        this.quantidade += quantidade;
    }

    public String codigo() {
        return codigo;
    }

    public Produto produto() {
        return produto;
    }

    public double quantidade() {
        return quantidade;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.codigo);
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
        final Lote other = (Lote) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
