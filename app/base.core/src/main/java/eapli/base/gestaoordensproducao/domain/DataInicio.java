package eapli.base.gestaoordensproducao.domain;

import eapli.base.utils.DateTime;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;


@Embeddable
public class DataInicio implements ValueObject{
    
    private static final long serialVersionUID = 1L;

    @Temporal(javax.persistence.TemporalType.DATE)
    private final Calendar dataInicio;

    
    public DataInicio(final String data) {
        Preconditions.nonEmpty(data, "sem data de instalacao!");
        Preconditions.ensure(formatoValido(data), "formato de data de instalação inválido![ dd/MM/yyyy ]");
        this.dataInicio = DateTime.parseDate(data, "dd/MM/yyyy");
        Preconditions.nonNull(dataInicio, "Sem data de inicio!");       
    }

    protected DataInicio() {
        this.dataInicio = null;
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
    
    public Calendar dataInicio(){
        return dataInicio;
    }
    
    @Override
    public int hashCode() {
        return dataInicio.hashCode();
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
        final DataInicio other = (DataInicio) obj;
        if (!Objects.equals(this.dataInicio, other.dataInicio)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
        return sdf.format(dataInicio.getTime());
    }

}
