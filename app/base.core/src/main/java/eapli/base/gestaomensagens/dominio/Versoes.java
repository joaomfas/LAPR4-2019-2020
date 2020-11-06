package eapli.base.gestaomensagens.dominio;

/*
    Protocol version number, a single byte representing an unsigned integer number 
    within the 0-255 range. The first version to be supported will have number zero.
*/

public enum Versoes {
    
    VERS_0   (0);
    
    private int ver;

    private Versoes(int ver) {
        this.ver = ver;
    }

    public int getVersao() {
        return ver;
    }
   
}
