package eapli.base.spm.application.processamento.validacao.exceptions;

public class RetornoAtividadeException extends SPMException {
    
    public RetornoAtividadeException() {
        
        super(EnumErros.RETORNO_ATIVIDADE.err());
        
    }
    
    @Override
    public EnumErros name() {
        return EnumErros.RETORNO_ATIVIDADE;
    }
}
