
package eapli.base.gestaolinhasproducao.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;

/**
 *
 * @author mdias
 */
@Embeddable
public class DataProcessamento implements ValueObject{
    private static final long serialVersionUID = 1L;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private final Date dataProcessamento;

    
    public DataProcessamento(final Date data) {
        Preconditions.nonNull(data, "sem data de processamento!");
        this.dataProcessamento = data;
      
    }

    protected DataProcessamento() {
        this.dataProcessamento = null;
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
    
    public Date dataProcessamento(){
        return dataProcessamento;
    }
    
    @Override
    public int hashCode() {
        return dataProcessamento.hashCode();
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
        final DataProcessamento other = (DataProcessamento) obj;
        if (!Objects.equals(this.dataProcessamento, other.dataProcessamento)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
        return sdf.format(dataProcessamento.getTime());
    }

}
