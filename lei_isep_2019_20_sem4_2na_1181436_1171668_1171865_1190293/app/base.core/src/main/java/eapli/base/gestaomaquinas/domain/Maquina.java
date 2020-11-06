package eapli.base.gestaomaquinas.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;
import java.net.InetAddress;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity
public class Maquina implements AggregateRoot<CodInterno> {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    private static int DEFAULT_TIMEOUT = 60;

    @EmbeddedId
    private CodInterno codInterno;
    @Column
    private String fichParaEnvio;
    @Column(nullable = false)
    private Descricao descricao;
    @Column(nullable = false)
    private NumSerie numserie;
    @Column(nullable = false)
    private DataInstalacao datainstalacao;
    @Column(nullable = false)
    private Marca marca;
    @Column(nullable = false)
    private Modelo modelo;
    @Column(nullable = false)
    private NumeroIdentificacaoUnico numeroIdentificacaoUnico;
    @Column
    private int timeOut;  // 30 segundos valor configuravel.
    @Transient
    private InetAddress ipAddress;
    @Transient
    private long lastUpdate;
    @ElementCollection
    private Set<MaquinaConfigFile> ficheirosConfiguracao = new HashSet<>();

    protected Maquina() {
    }

    public Maquina(CodInterno codInterno, Descricao descricao, NumSerie numserie, DataInstalacao datainstalacao, Marca marca, Modelo modelo, NumeroIdentificacaoUnico numeroIdentificacaoUnico) {
        requisitosCriacaoMaquina(codInterno, descricao, numserie, datainstalacao, marca, modelo, numeroIdentificacaoUnico);
        this.codInterno = codInterno;
        this.descricao = descricao;
        this.numserie = numserie;
        this.datainstalacao = datainstalacao;
        this.marca = marca;
        this.modelo = modelo;
        this.numeroIdentificacaoUnico = numeroIdentificacaoUnico;
        this.timeOut = DEFAULT_TIMEOUT;
        this.lastUpdate = 0;
        this.fichParaEnvio = "";
    }

    public void setFichParaEnvio(String fichParaEnvio) {
        this.fichParaEnvio = fichParaEnvio;
    }

    public String getFichParaEnvio() {
        return fichParaEnvio;
    }
    
    private void requisitosCriacaoMaquina(CodInterno codInterno, Descricao descricao, NumSerie numserie, DataInstalacao datainstalacao, Marca marca, Modelo modelo, NumeroIdentificacaoUnico numeroIdentificacaoUnico) {
        Preconditions.noneNull(codInterno, "Código Interno não pode ser nulo");
        Preconditions.noneNull(descricao, "Descrição não pode ser nulo");
        Preconditions.noneNull(numserie, "Número de Série não pode ser nulo");
        Preconditions.noneNull(datainstalacao, "Data de Instalação não pode ser nulo");
        Preconditions.noneNull(marca, "Marca não pode ser nulo");
        Preconditions.noneNull(modelo, "Modelo não pode ser nulo");
        Preconditions.noneNull(numeroIdentificacaoUnico, "Número de identificação não pode ser nulo");
    }

    public boolean adicionarConfigFile(byte[] fichBytes, String descricao) {
        MaquinaConfigFile configFile = new MaquinaConfigFile(fichBytes, descricao);
        return ficheirosConfiguracao.add(configFile);
    }

    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }

    public Set<MaquinaConfigFile> ficheirosConfig() {
        return this.ficheirosConfiguracao;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.codInterno);
        hash = 31 * hash + Objects.hashCode(this.numeroIdentificacaoUnico);
        return hash;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Maquina)) {
            return false;
        }

        final Maquina that = (Maquina) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity())
                && codInterno.equals(that.codInterno)
                && descricao.equals(that.descricao) && datainstalacao.equals(that.datainstalacao)
                && marca.equals(that.marca) && modelo.equals(that.modelo);
    }

    public void update() {
        lastUpdate = System.currentTimeMillis();
    }

    public Estado estado() {
        Long diferenca = (System.currentTimeMillis() - lastUpdate) / 1000;
        if (diferenca > timeOut) {
            return Estado.INDISPONIVEL;
        } else {
            return Estado.DISPONIVEL;
        }
    }

    public void especificarIP(InetAddress ipAddress) {
        this.ipAddress = ipAddress;
    }

    public InetAddress ipAddress() {
        return ipAddress;
    }

    @Override
    public CodInterno identity() {
        return this.codInterno;
    }

    public NumeroIdentificacaoUnico numeroIdentificacaoUnico() {
        return numeroIdentificacaoUnico;
    }

    public CodInterno codInterno() {
        return codInterno;
    }

    public Descricao descricao() {
        return descricao;
    }

    public NumSerie numserie() {
        return numserie;
    }

    public DataInstalacao datainstalacao() {
        return datainstalacao;
    }

    public Marca marca() {
        return marca;
    }

    public Modelo modelo() {
        return modelo;
    }

    public int timeOut() {
        return timeOut;
    }

    @Override
    public String toString() {
        return "Maquina{" + "codInterno=" + codInterno + ", descricao=" + descricao + ", datainstalacao=" + datainstalacao + ", marca=" + marca + ", modelo=" + modelo + ", numeroIdentificacaoUnico=" + numeroIdentificacaoUnico + '}';
    }
    
    public void updateFicheiro(MaquinaConfigFile file){
        MaquinaConfigFile fichAApagar = null;
        for(MaquinaConfigFile f : ficheirosConfiguracao){
            if(f.toString().equals(file.toString())){
                fichAApagar = f;
                break;
            }
        }
        if(fichAApagar != null){
            ficheirosConfiguracao.remove(fichAApagar);
        }
    }

    public boolean validaIdentidadeMensagem(InetAddress ip, NumeroIdentificacaoUnico nui) {
        return this.ipAddress.equals(ip) && this.numeroIdentificacaoUnico.equals(nui);
    }
}
