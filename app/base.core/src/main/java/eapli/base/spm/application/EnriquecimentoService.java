package eapli.base.spm.application;

public class EnriquecimentoService {
    
    private static Thread thrLinhaEnriquecimento = null;
    
    public boolean iniciaEnriquecimento() {
        if(thrLinhaEnriquecimento == null) {
            thrLinhaEnriquecimento = new Thread(new LinhaProducaoEnriquecimentoRunnable(50, 1000));
            thrLinhaEnriquecimento.start();
            return true;
        }
        return false;
    }   
}