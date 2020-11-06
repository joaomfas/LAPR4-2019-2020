package eapli.base.gestaoordensproducao.domain;

import eapli.base.gestaodepositos.domain.Deposito;
import eapli.base.gestaoprodutos.domain.Produto;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class Producao implements ValueObject {

    private static final long serialVersionUID = 1L;

    @ManyToOne()
    private final Produto itemProduto;
    private final Double quantidade;
    @ManyToOne()
    private final Deposito deposito;
    private Double desvio;
    private final String lote;
    @Temporal(TemporalType.DATE)
    private final Calendar dataRegisto;

    public Producao(final Produto itemProduto, final Double quantidade, Deposito deposito, String lote, Calendar dataRegisto, Double desvio) {
        requisitosComponenteProduto(itemProduto, quantidade, deposito, dataRegisto);
        this.itemProduto = itemProduto;
        this.quantidade = quantidade;
        this.deposito = deposito;
        this.lote = lote;
        this.dataRegisto = dataRegisto;
        this.desvio = desvio;
    }

    private void requisitosComponenteProduto(Produto itemProduto, Double quantidade, Deposito deposito, Calendar dataRegisto) {
        Preconditions.nonNull(itemProduto, "Produto não pode ser null!");
        Preconditions.nonNull(deposito, "Depósito não pode ser null!");
        Preconditions.nonNull(dataRegisto, "Data de Registo não pode ser null!");
        Preconditions.ensure(quantidade > 0, "Quantide não pode ser negativa!");
    }


    protected Producao() {
        this.itemProduto = null;
        this.quantidade = null;
        this.lote = null;
        this.deposito = null;
        this.dataRegisto = null;
        this.desvio = null;
    }
    
    public Produto produto(){
        return itemProduto;
    }
    
    public Double quantidade(){
        return quantidade;
    }
    
    public Double desvio(){
        return desvio;
    }
    
    public void updateDesvio(Double desvio) {
        this.desvio = desvio;
    }
    
    public Deposito deposito(){
        return deposito;
    }
    
    public String lote(){
        return lote;
    }
    
    public Calendar dataRegisto(){
        return dataRegisto;
    }
    
    public String dataRegistoStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
        return sdf.format(dataRegisto.getTime());
    }
}
