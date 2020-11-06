package eapli.base.gestaomaquinas.domain;

public enum Estado {
    DISPONIVEL (1),
    INDISPONIVEL (0);
    
    private int estado;

    private Estado(int estado) {
        this.estado = estado;
    }

    public int getEstado() {
        return estado;
    }
    
}
