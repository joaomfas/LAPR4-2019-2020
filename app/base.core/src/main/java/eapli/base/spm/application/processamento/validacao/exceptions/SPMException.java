package eapli.base.spm.application.processamento.validacao.exceptions;

public class SPMException extends Exception implements ISPMException {

    public SPMException(String err) {
        
        super(EnumErros.SPM + " [ " + err + " ] ");
        
    }
    
    /**
     * Tipo de Erros de processamento de 
     * mensagens a serem persistidos.
     * @return Nome do erro
     */
    @Override
    public EnumErros name() {
        return EnumErros.SPM;
    }
    
}
