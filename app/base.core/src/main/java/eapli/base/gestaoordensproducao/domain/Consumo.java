package eapli.base.gestaoordensproducao.domain;

import eapli.base.gestaodepositos.domain.Deposito;
import eapli.base.gestaomateriasprimas.domain.MateriaPrima;
import eapli.base.gestaoprodutos.domain.Produto;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class Consumo implements ValueObject {

    private static final long serialVersionUID = 1L;
    
    @ManyToOne()
    private final Produto itemProduto;
    @ManyToOne()
    private final MateriaPrima itemMP;
    private final Double quantidade;
    @ManyToOne()
    private final Deposito deposito;
    private Double desvio;
    private final Calendar dataRegisto;

    public Consumo(final Produto itemProduto, final Double quantidade, Deposito deposito, Double desvio, Calendar dataRegisto) {
        requisitosComponenteProduto(itemProduto, deposito, quantidade, dataRegisto);
        this.itemProduto = itemProduto;
        this.itemMP = null;
        this.deposito = deposito;
        this.quantidade = quantidade;
        this.desvio = desvio;
        this.dataRegisto = dataRegisto;
    }
    
    public Consumo(final MateriaPrima itemMP, final Double quantidade, Deposito deposito, Double desvio, Calendar dataRegisto) {
        requisitosComponenteMateriaPrima(itemMP, deposito, quantidade, dataRegisto);
        this.itemProduto = null;
        this.itemMP = itemMP;
        this.deposito = deposito;
        this.quantidade = quantidade;
        this.desvio = desvio;
        this.dataRegisto = dataRegisto;
    }

    private void requisitosComponenteProduto(Produto componente, Deposito deposito, Double quantidade, Calendar dataRegisto) {
        Preconditions.nonNull(componente, "Componente não pode ser null!");
        Preconditions.nonNull(deposito, "Depósito não pode ser null!");
        Preconditions.nonNull(dataRegisto, "Data de Registo não pode ser null!");
        Preconditions.ensure(quantidade > 0, "Quantidade não pode ser negativa!");
    }
    
     private void requisitosComponenteMateriaPrima(MateriaPrima componente, Deposito deposito, Double quantidade, Calendar dataRegisto) {
        Preconditions.nonNull(componente, "Componente não pode ser null!");
        Preconditions.nonNull(deposito, "Depósito não pode ser null!");
        Preconditions.nonNull(dataRegisto, "Data de Registo não pode ser null!");
        Preconditions.ensure(quantidade > 0, "Quantidade não pode ser negativa!");
    }

    protected Consumo() {
        this.itemMP = null;
        this.itemProduto = null;
        this.deposito = null;
        this.desvio = null;
        this.quantidade = null;
        this.dataRegisto = null;
    }
    
    public Produto produto(){
        return this.itemProduto;
    }
    
    public MateriaPrima materiaPrima(){
        return this.itemMP;
    }
    
    public boolean isProduto(){
        return this.itemMP == null;
    }
    
    public Deposito deposito(){
        return deposito;
    }
    
    public Double desvio(){
        return desvio;
    }
    
    public void updateDesvio(double desvio) {
        this.desvio = desvio;
    }
    
    public Double quantidade(){
        return quantidade;
    }
    
    public Calendar dataRegisto(){
        return dataRegisto;
    }
    
    public String dataRegistoStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
        return sdf.format(dataRegisto.getTime());
    }
}
