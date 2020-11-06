package eapli.base.spm.application.processamento.validacao.exceptions;

public class ProdutoInvalidoException extends SPMException {

    public ProdutoInvalidoException() {
        
        super(EnumErros.PRODUTO_INVALIDO.err());
        
    }
    
    @Override
    public EnumErros name() {
        return EnumErros.PRODUTO_INVALIDO;
    }
    
}
