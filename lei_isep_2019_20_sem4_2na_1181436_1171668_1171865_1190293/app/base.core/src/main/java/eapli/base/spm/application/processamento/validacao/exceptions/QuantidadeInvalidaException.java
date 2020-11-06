package eapli.base.spm.application.processamento.validacao.exceptions;

public class QuantidadeInvalidaException extends SPMException {
    
    public QuantidadeInvalidaException() {
        
        super(EnumErros.QUANTIDADE_INVALIDA.err());
        
    }
    
    @Override
    public EnumErros name() {
        return EnumErros.QUANTIDADE_INVALIDA;
    }
    
}
