package eapli.base.spm.application.processamento;

import eapli.base.gestaomaquinas.domain.CodInterno;
import eapli.base.gestaomaquinas.domain.Maquina;
import eapli.base.gestaomaquinas.repositories.MaquinaRepository;
import eapli.base.gestaomensagens.dominio.Mensagem;
import eapli.base.gestaomensagens.repositories.MensagemRepository;
import eapli.base.gestaoordensproducao.repositories.ExecucaoOrdemProducaoRepository;
import eapli.base.gestaoordensproducao.repositories.OrdemProducaoRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.spm.application.processamento.validacao.exceptions.DataInvalidaException;
import eapli.base.spm.application.processamento.validacao.exceptions.MaquinaInvalidaException;
import eapli.base.spm.application.processamento.validacao.exceptions.NumeroCamposInvalidoException;
import eapli.base.spm.application.processamento.validacao.exceptions.SPMException;
import eapli.base.spm.application.processamento.validacao.exceptions.TipoMensagemInvalidoException;
//import eapli.base.spm.application.processamento.validardecorator.Validar;
//import eapli.base.spm.application.processamento.validardecorator.ValidarMensagem;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

public abstract class ProcessaMensagem {
    
    protected final MensagemRepository mensagemRepository = PersistenceContext.repositories().mensagens();
    protected final OrdemProducaoRepository ordemProducaoRepository = PersistenceContext.repositories().ordensProducao();
    protected final ExecucaoOrdemProducaoRepository execOrdemProducaoRepository = PersistenceContext.repositories().execucaoOrdensProducao();
    protected final MaquinaRepository maquinaRepository = PersistenceContext.repositories().maquinas();
    
    protected final Mensagem mensagem;
    protected String[] campos;
//    protected Validar validacao;
    
    public ProcessaMensagem(Mensagem mensagem) throws SPMException {
//        validacao = new ValidarMensagem(mensagem);
//        validacao.valida();
        try {
        this.campos = mensagem.rawData().toString().split(";");
        for(int i=0;i<this.campos.length; i++)
            this.campos[i] = this.campos[i].trim();
        } catch (Exception ex) {
            throw new NumeroCamposInvalidoException();
        }
        this.mensagem = mensagem;
    }

    
    public Maquina maquina() throws SPMException {
        Optional<Maquina> oMaq = maquinaRepository.ofIdentity(new CodInterno(campos[0]));
        if(!oMaq.isPresent())
            throw new MaquinaInvalidaException();
        else
            return maquinaRepository.ofIdentity(new CodInterno(campos[0])).get();
    }

    public Tipo tipo() throws SPMException {
        try {
            return Tipo.valueOf(campos[1].trim().toUpperCase());
        } catch (Exception ex) {
            throw new TipoMensagemInvalidoException();
        }
    }
    
    public Long date() throws DataInvalidaException {
        return buildCalendar().getTime().getTime();
    }
    
    public Calendar buildCalendar() throws DataInvalidaException {
        try {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(campos[2].substring(0, 4)));
        cal.set(Calendar.MONTH, Integer.parseInt(campos[2].substring(4, 6)));
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(campos[2].substring(6, 8)));
        cal.set(Calendar.HOUR, Integer.parseInt(campos[2].substring(8, 10)));
        cal.set(Calendar.MINUTE, Integer.parseInt(campos[2].substring(10, 12)));
        cal.set(Calendar.SECOND, Integer.parseInt(campos[2].substring(12, 14)));
        return cal;
        } catch (Exception ex) {
            throw new DataInvalidaException(); 
        }
    }
    
    public String stringData() throws DataInvalidaException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
        return sdf.format(buildCalendar().getTime());    
    }
    
    
    public boolean primeiraMaquina() throws SPMException {
        if(mensagem.obterLinhaProducao()!=null) {
            if(mensagem.obterLinhaProducao().listaSequencia().size()>0)
            if(mensagem.obterLinhaProducao().listaSequencia().get(0).maquina().equals(maquina())) {
                return true;
            }
        }
        return false;
    }

    public boolean ultimaMaquina() throws SPMException {
        if(mensagem.obterLinhaProducao()!=null) {
            int size = mensagem.obterLinhaProducao().listaSequencia().size();
            if(size>0)
            if(mensagem.obterLinhaProducao().listaSequencia().get(size-1).maquina().equals(maquina())) {
                return true;
            }
        }
        return false;
    }

    public Mensagem mensagem() {
        return mensagem;
    }
    
    public abstract boolean processa() throws SPMException;
    
    
}
