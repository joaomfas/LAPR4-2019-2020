package eapli.base.gestaoordensproducao.domain;

import eapli.base.gestaodepositos.domain.Deposito;
import eapli.base.gestaomateriasprimas.domain.MateriaPrima;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class Estorno implements ValueObject{
private static final long serialVersionUID = 1L;

    @ManyToOne()
    private final MateriaPrima materiaPrima;
    private final Double quantidade;
    @ManyToOne()
    private final Deposito deposito;
    private final Calendar dataRegisto;

    public Estorno(final MateriaPrima materiaPrima, final Double quantidade, Deposito deposito, Calendar dataRegisto) {
        requisitosComponenteProduto(materiaPrima, quantidade, deposito, dataRegisto);
        this.materiaPrima = materiaPrima;
        this.quantidade = quantidade;
        this.deposito = deposito;
        this.dataRegisto = dataRegisto;
    }

    private void requisitosComponenteProduto(MateriaPrima materiaPrima, Double quantidade, Deposito deposito, Calendar dataRegisto) {
        Preconditions.nonNull(materiaPrima, "Matéria prima não pode ser null!");
        Preconditions.nonNull(deposito, "Depósito não pode ser null!");
        Preconditions.nonNull(dataRegisto, "Data de Registo não pode ser null!");
        Preconditions.ensure(quantidade > 0, "Quantide não pode ser negativa!");
    }


    protected Estorno() {
        this.materiaPrima = null;
        this.quantidade = null;
        this.deposito = null;
        this.dataRegisto = null;
    }
    
    public MateriaPrima materiaPrima(){
        return materiaPrima;
    }
    
    public Double quantidade(){
        return quantidade;
    }
    
    public Deposito deposito(){
        return deposito;
    }
    
    public Calendar dataRegisto(){
        return dataRegisto;
    }
    
    public String dataRegistoStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
        return sdf.format(dataRegisto.getTime());
    }
}
