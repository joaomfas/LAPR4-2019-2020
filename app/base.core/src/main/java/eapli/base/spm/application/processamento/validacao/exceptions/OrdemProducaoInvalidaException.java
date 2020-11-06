package eapli.base.spm.application.processamento.validacao.exceptions;

public class OrdemProducaoInvalidaException extends SPMException {
    
    public OrdemProducaoInvalidaException() {
        super(EnumErros.ORDEM_PRODUCAO_INVALIDA.err());
    }
    
    @Override
    public EnumErros name() {
        return EnumErros.ORDEM_PRODUCAO_INVALIDA;
    }
    
}
