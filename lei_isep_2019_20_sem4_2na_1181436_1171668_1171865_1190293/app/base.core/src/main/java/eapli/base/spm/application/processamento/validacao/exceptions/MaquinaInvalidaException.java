package eapli.base.spm.application.processamento.validacao.exceptions;

public class MaquinaInvalidaException extends SPMException {

    public MaquinaInvalidaException() {
        
        super(EnumErros.MAQUINA_INVALIDA.err());
        
    }
    
    @Override
    public EnumErros name() {
        return EnumErros.MAQUINA_INVALIDA;
    }
    
}
