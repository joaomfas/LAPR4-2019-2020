package eapli.base.gestaomensagens.dominio;

public enum Codes {
    
    HELLO   (0),
    
    MSG     (1),
    
    CONFIG  (2),
    
    RESET   (3),
    
    ACK     (150),
    
    NACK    (151);
    
    private final int code;

    private Codes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
