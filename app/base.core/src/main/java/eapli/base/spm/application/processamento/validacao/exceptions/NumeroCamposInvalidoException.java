package eapli.base.spm.application.processamento.validacao.exceptions;

public class NumeroCamposInvalidoException extends SPMException {

    public NumeroCamposInvalidoException() {
        super(EnumErros.NUMERO_CAMPOS_INVALIDO.err());
    }
    
    @Override
    public EnumErros name() {
        return EnumErros.NUMERO_CAMPOS_INVALIDO;
    }
    
}
