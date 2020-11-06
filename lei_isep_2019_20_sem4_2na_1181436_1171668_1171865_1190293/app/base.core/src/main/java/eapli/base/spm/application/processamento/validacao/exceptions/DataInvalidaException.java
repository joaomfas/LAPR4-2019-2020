package eapli.base.spm.application.processamento.validacao.exceptions;

public class DataInvalidaException extends SPMException {

    public DataInvalidaException() {
        
        super(EnumErros.DATA_INVALIDA.err());
        
    }
    
    @Override
    public EnumErros name() {
        return EnumErros.DATA_INVALIDA;
    }
    
}
