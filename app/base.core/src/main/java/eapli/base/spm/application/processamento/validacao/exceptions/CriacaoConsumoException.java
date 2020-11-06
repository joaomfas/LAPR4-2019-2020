package eapli.base.spm.application.processamento.validacao.exceptions;

public class CriacaoConsumoException extends SPMException {

    public CriacaoConsumoException() {
        super(EnumErros.CRIACAO_CONSUMO.err());
    }
    
    @Override
    public EnumErros name() {
        return EnumErros.CRIACAO_CONSUMO;
    }
}
