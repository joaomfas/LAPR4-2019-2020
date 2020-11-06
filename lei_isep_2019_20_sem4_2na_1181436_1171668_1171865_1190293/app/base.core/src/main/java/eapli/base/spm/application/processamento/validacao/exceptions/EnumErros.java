package eapli.base.spm.application.processamento.validacao.exceptions;

public enum EnumErros {
    
    SPM("ERRO SPM"),
    
    FORMATACAO_INVALIDA("Formatacao Invalida!"),
    
    NUMERO_CAMPOS_INVALIDO("Numero de campos inválido!"),
    // |
    MAQUINA_INVALIDA("Maquina invalida!"),
    // |
    TIPO_MENSAGEM_INVALIDO("Tipo de mensagem Invalido!"),
    // |
    DATA_INVALIDA("Data da mensagem inválida!"),
    // |
    PRODUTO_INVALIDO("Produto Inválido!"),
    // |
    QUANTIDADE_INVALIDA("Quantidade Inválida!"),
    // |
    DEPOSITO_INVALIDO("Depósito Inválido!"),
    // |
    LINHA_PRODUCAO_MAQUINA_INVALIDA("Maquina não pertence à linha de Produção!"),
    // |
    ORDEM_PRODUCAO_INVALIDA("Ordem Producao Inválida!"),
    // |
    OUTRA_ORDEM_PRODUCAO_INICIADA("Maquina iniciou nova ordem de producão sem terminar a anterior"),
    // |
    OUTRA_ORDEM_PRODUCAO_TERMINADA("Maquina tentou terminar uma ordem de producão diferente da ordem atual"),
    // |
    CRIACAO_CONSUMO("Não foi possivel registar consumo"),
    // |
    RETORNO_ATIVIDADE("Maquina retornou atividade, recuperou de um erro");
    
    
    
    private String err;
    
    private EnumErros(String err) {
        this.err = err;
    }
    
    public String err() {
        return err;
    }
    
}
