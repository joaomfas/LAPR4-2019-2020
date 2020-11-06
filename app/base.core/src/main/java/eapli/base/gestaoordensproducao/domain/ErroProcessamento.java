package eapli.base.gestaoordensproducao.domain;

import eapli.base.gestaomensagens.dominio.Mensagem;
import eapli.base.spm.application.processamento.validacao.exceptions.EnumErros;
import eapli.framework.domain.model.AggregateRoot;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

@Entity
public class ErroProcessamento implements AggregateRoot<Long> {
    
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;    
    EstadoErro estado;
    EnumErros tipo;
    @OneToOne
    Mensagem mensagem;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataGeracao;

    public ErroProcessamento() {
    }

    public ErroProcessamento(EnumErros tipo, Mensagem mensagem) {
        this.estado = EstadoErro.PENDENTE;
        this.tipo = tipo;
        this.mensagem = mensagem;
        this.dataGeracao = new Date();
    }
    
    
    public void arquivarErro() {
        this.estado = EstadoErro.ARQUIVADO;
    }
    
    public Date dataErro(){
        return this.dataGeracao;
    }
    
    public String dataErroStr(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
        return sdf.format(this.dataGeracao);
    }
    
    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof ErroProcessamento)) {
            return false;
        }
        
        final ErroProcessamento that = (ErroProcessamento) other;
        if (this == that) {
            return true;
        }
        
        return identity().equals(that.identity());
    }
    
    public Mensagem mensagem() {
        return this.mensagem;
    }
    
    public EnumErros tipoErro(){
        return this.tipo;
    }
    
    public EstadoErro estado(){
        return this.estado;
    }

    @Override
    public Long identity() {
        return this.id;
    }
}
