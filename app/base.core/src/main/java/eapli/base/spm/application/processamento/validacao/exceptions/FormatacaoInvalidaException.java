package eapli.base.spm.application.processamento.validacao.exceptions;

public class FormatacaoInvalidaException extends SPMException {

    public FormatacaoInvalidaException() {
        
        super(EnumErros.FORMATACAO_INVALIDA.err());
        
    }
    
    @Override
    public EnumErros name() {
        return EnumErros.FORMATACAO_INVALIDA;
    }
    
}
