package eapli.base.spm.application.processamento;

import eapli.base.spm.application.processamento.validacao.exceptions.DataInvalidaException;
import eapli.base.spm.application.processamento.validacao.exceptions.SPMException;
import eapli.base.gestaomensagens.dominio.Mensagem;
import eapli.base.gestaoordensproducao.domain.ExecucaoOrdemProducao;
import java.util.Iterator;

//S1 -> Retoma de Atividade
//S1 -> Máquina;TipoMsg;DataHora;Erro
public class S1 extends ProcessaMensagem {
    
    public S1(Mensagem mensagem) throws SPMException {
        super(mensagem);
    }
    
    public String erro() throws DataInvalidaException {
        try {
            return campos[5];
        } catch (Exception ex) {
            throw new DataInvalidaException();
        }
    }
    
    
    @Override
    public boolean processa() throws SPMException {
        if(mensagem.obterOrdemProducao()==null) return false;
        
        Iterator<ExecucaoOrdemProducao> execucaoDaOrdensProducao = this.execOrdemProducaoRepository.execucaoDaOrdensProducao(mensagem.obterOrdemProducao()).iterator();
        if(execucaoDaOrdensProducao.hasNext()) {
            ExecucaoOrdemProducao execOrdProd = execucaoDaOrdensProducao.next();
            execOrdProd.registarRetomaAtividade(maquina(), buildCalendar());
            this.execOrdemProducaoRepository.save(execOrdProd);
        }
        
        return false;
    }
    
}
