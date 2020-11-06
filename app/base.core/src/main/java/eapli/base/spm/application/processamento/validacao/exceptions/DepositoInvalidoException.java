package eapli.base.spm.application.processamento.validacao.exceptions;

public class DepositoInvalidoException extends SPMException {

    public DepositoInvalidoException() {
        super(EnumErros.DEPOSITO_INVALIDO.err());
    }
    
    @Override
    public EnumErros name() {
        return EnumErros.DEPOSITO_INVALIDO;
    }
    
    
}
