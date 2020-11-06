package eapli.base.gestaomensagens.dominio;

import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import eapli.base.gestaomensagens.application.SCMServerThreadLinha;
import eapli.base.gestaoordensproducao.domain.OrdemProducao;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity
public class Mensagem implements AggregateRoot<Long>, Comparator<Mensagem> {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codId;
    @Transient
    private Versao versao;
    @Transient
    private Code code;
    @Transient
    private Uid id;
    @Transient
    private DataLength dataLength;
    @Column
    private RawData rawData;
    @Column
    private boolean mensagemProcessada;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataGeracao;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataRecepcao;

    @OneToOne
    private LinhaProducao linhaProducao;

    @OneToOne
    private OrdemProducao ordemProducao;

    public Mensagem() {
        versao = null;
        code = null;
        id = null;
        dataLength = null;
        rawData = null;
        dataGeracao = null;
        dataRecepcao = null;
    }

    public Mensagem(String data, Date dataGeracao, Date dataRecepcao) {
        Preconditions.nonNull(data, "Mensagem não pode ser nula.");
        this.dataLength = new DataLength(data.length());
        this.rawData = new RawData(data);
        this.mensagemProcessada = false;
        this.dataGeracao = dataGeracao;
        this.dataRecepcao = dataRecepcao;
    }

    public Mensagem(int versao, int code, int uid, int dataLength, String data) {
        Preconditions.nonNull(data, "Mensagem não pode ser null.");
        Preconditions.nonNull(versao, "Versao não pode ser null.");
        Preconditions.ensure(verificarDataLength(dataLength, data), "Tamanho da string não válido.");
        this.versao = new Versao(versao);
        this.code = new Code(code);
        this.id = new Uid(uid);
        this.dataLength = new DataLength(dataLength);
        this.rawData = new RawData(data);
        this.mensagemProcessada = false;
        this.dataGeracao = null;
        this.dataRecepcao = null;
        this.linhaProducao = null;
        this.linhaProducao = null;
    }
    
    public Mensagem(int versao, int code, int uid, int dataLength, byte[] data) {
        Preconditions.nonNull(data, "Mensagem não pode ser null.");
        Preconditions.nonNull(versao, "Versao não pode ser null.");
        Preconditions.ensure(verificarDataLength(dataLength, data), "Tamanho do raw data não válido.");
        this.versao = new Versao(versao);
        this.code = new Code(code);
        this.id = new Uid(uid);
        this.dataLength = new DataLength(dataLength);
        this.rawData = new RawData(data);
        this.mensagemProcessada = false;
        this.dataGeracao = null;
        this.dataRecepcao = null;
        this.linhaProducao = null;
        this.linhaProducao = null;
    }

    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.codId);
        return hash;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Mensagem)) {
            return false;
        }

        final Mensagem that = (Mensagem) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity());
    }

    @Override
    public Long identity() {
        return codId;
    }

    private boolean verificarDataLength(int dataLength, String data) {
        return data.length() == dataLength;
    }
    
    private boolean verificarDataLength(int dataLength, byte[] data) {
        return data.length == dataLength;
    }

    public void definirLinhaProducao(LinhaProducao linhaproducao) {
        this.linhaProducao = linhaproducao;
    }

    public void definirOrdemProducao(OrdemProducao ordProducao) {
        this.ordemProducao = ordProducao;
    }

    public void marcarComoProcessada() {
        this.mensagemProcessada = true;
    }

    public int versao() {
        return versao.value();
    }

    public int code() {
        return code.value();
    }

    public int id() {
        return id.value();
    }

    public int dataLength() {
        return dataLength.value();
    }

    public RawData rawData() {
        return rawData;
    }

    public void updateDataGeracao(Date dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public void updateDataRecepcao(Date dataRecepcao) {
        this.dataRecepcao = dataRecepcao;
    }

    public Date dataGeracao() {
        return this.dataGeracao;
    }

    public Date dataRecepcao() {
        return this.dataRecepcao;
    }

    public LinhaProducao obterLinhaProducao() {
        return this.linhaProducao;
    }

    public OrdemProducao obterOrdemProducao() {
        return this.ordemProducao;
    }

    public boolean mensagemPorProcessar() {
        return !mensagemProcessada;
    }
    
    public boolean mensagensSemLinhaProducao() {
        return linhaProducao==null;
    }

    public boolean mensagemComOrdemProducao() {
        return this.ordemProducao != null;
    }

    @Override
    public String toString() {
        return "Mensagem{" + "versao=" + versao + ", code=" + code + ", id=" + id + ", dataLength=" + dataLength + ", rawData=" + rawData + '}';
    }

    @Override
    public int compare(Mensagem m1, Mensagem m2) {
        if (m1.dataGeracao().after(m2.dataGeracao())) {
            return 1;
        } else {
            return -1;
        }
    }

    public boolean validaMensagemHello() {
        //Recebe e valida a versão do protocolo de comunicação
        boolean versaoValida = false;
        for (Versoes v : Versoes.values()) {
            if (v.getVersao() == this.versao()) {
                versaoValida = true;
            }
        }
        if (!versaoValida) {
            throw new IllegalStateException("Maquina com versão do protocolo de comunicação errada.");
        }

        //Verifica se é uma mensagem HELLO
        if (this.code() != Codes.HELLO.getCode()) {
            throw new IllegalStateException("A primeira mensagem não é HELLO.");
        }
        
        return true;
    }
    
    public boolean isTypeMSG(){
        return this.code.value() == Codes.MSG.getCode();
    }
}
