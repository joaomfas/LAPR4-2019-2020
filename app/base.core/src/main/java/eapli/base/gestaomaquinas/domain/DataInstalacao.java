package eapli.base.gestaomaquinas.domain;

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
public class DataInstalacao implements ValueObject{
    
    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private final Calendar dataInstalacao;

    
    public DataInstalacao(final String data) {
        Preconditions.nonEmpty(data, "sem data de instalacao!");
        Preconditions.ensure(formatoValido(data), "formato de data de instalação inválido![ dd/MM/yyyy ]");
        this.dataInstalacao = DateTime.parseDate(data, "dd/MM/yyyy");
        Preconditions.nonNull(dataInstalacao, "Sem data de instalação!");
        Preconditions.ensure(dataInstalacao.getTime().getTime()<Calendar.getInstance().getTime().getTime(), "Não é possivel especificar uma data de instalação futura.");        
    }

    protected DataInstalacao() {
        this.dataInstalacao = null;
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
        return dataInstalacao.hashCode();
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
        final DataInstalacao other = (DataInstalacao) obj;
        if (!Objects.equals(this.dataInstalacao, other.dataInstalacao)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
        return sdf.format(dataInstalacao.getTime());
    }

}
