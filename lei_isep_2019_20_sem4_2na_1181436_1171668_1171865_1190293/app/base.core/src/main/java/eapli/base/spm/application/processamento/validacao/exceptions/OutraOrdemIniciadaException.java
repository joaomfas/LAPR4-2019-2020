package eapli.base.spm.application.processamento.validacao.exceptions;

public class OutraOrdemIniciadaException extends SPMException {

    public OutraOrdemIniciadaException() {
        
        super(EnumErros.OUTRA_ORDEM_PRODUCAO_INICIADA.err());
        
    }
    
    @Override
    public EnumErros name() {
        return EnumErros.OUTRA_ORDEM_PRODUCAO_INICIADA;
    }
}
