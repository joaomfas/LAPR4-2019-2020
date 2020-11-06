package eapli.base.spm.application.processamento.validacao.exceptions;

public class OutraOrdemTerminadaException extends SPMException {

    public OutraOrdemTerminadaException() {
        
        super(EnumErros.OUTRA_ORDEM_PRODUCAO_TERMINADA.err());
        
    }
    
    @Override
    public EnumErros name() {
        return EnumErros.OUTRA_ORDEM_PRODUCAO_TERMINADA;
    }
}
