package eapli.base.spm.application.processamento.validacao.exceptions;

public class MaquinaNaoPertenceLinhaProducaoException extends SPMException {

    public MaquinaNaoPertenceLinhaProducaoException() {
        
        super(EnumErros.LINHA_PRODUCAO_MAQUINA_INVALIDA.err());
        
    }
    
    @Override
    public EnumErros name() {
        return EnumErros.LINHA_PRODUCAO_MAQUINA_INVALIDA;
    }
    
}
