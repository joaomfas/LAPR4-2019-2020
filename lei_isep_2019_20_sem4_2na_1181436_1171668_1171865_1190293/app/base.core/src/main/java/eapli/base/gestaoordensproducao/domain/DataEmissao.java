package eapli.base.gestaoordensproducao.domain;

import eapli.base.utils.DateTime;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;


@Embeddable
public class DataEmissao implements ValueObject{
    
    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private final Calendar dataEmissao;

    
    public DataEmissao(final String data) {
        Preconditions.nonEmpty(data, "sem data de instalacao!");
        Preconditions.ensure(formatoValido(data), "formato de data de instalação inválido![ dd/MM/yyyy ]");
        this.dataEmissao = DateTime.parseDate(data, "dd/MM/yyyy");
        Preconditions.nonNull(dataEmissao, "Sem data de emissão!");
        Preconditions.ensure(dataEmissao.getTime().getTime()<Calendar.getInstance().getTime().getTime(), "Não é possivel especificar uma data de emissão no futuro.");
    }

    protected DataEmissao() {
        this.dataEmissao = null;
    }

    private boolean formatoValido(String dateStr) {
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return dataEmissao.hashCode();
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
        final DataEmissao other = (DataEmissao) obj;
        if (!Objects.equals(this.dataEmissao, other.dataEmissao)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
        return sdf.format(dataEmissao.getTime());
    }

}
