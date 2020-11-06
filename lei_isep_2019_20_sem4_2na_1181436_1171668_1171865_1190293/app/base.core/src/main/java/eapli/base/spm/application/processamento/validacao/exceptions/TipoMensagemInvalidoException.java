package eapli.base.spm.application.processamento.validacao.exceptions;

public class TipoMensagemInvalidoException extends SPMException {
    
    public TipoMensagemInvalidoException() {
        
        super(EnumErros.TIPO_MENSAGEM_INVALIDO.err());
        
    }
    
    @Override
    public EnumErros name() {
        return EnumErros.TIPO_MENSAGEM_INVALIDO;
    }

}
