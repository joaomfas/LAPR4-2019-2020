package eapli.base.gestaoprodutos.domain;

import eapli.base.gestaomateriasprimas.domain.MateriaPrima;
import java.util.ArrayList;
import java.util.List;

public class FichaProducaoBuilder{
    private List<Componente> listaComponentes;

    public FichaProducaoBuilder() {
        listaComponentes = new ArrayList<>();
    }

    public FichaProducaoBuilder adicionarProduto(Produto item, Double quantidade){
        Componente componente = new Componente(item, quantidade);
        listaComponentes.add(componente);
        return this;
    }
    
    public FichaProducaoBuilder adicionarMateriaPrima(MateriaPrima item, Double quantidade){
        Componente componente = new Componente(item, quantidade);
        listaComponentes.add(componente);
        return this;
    }
    
    public FichaProducao build(){
        FichaProducao fichaProducao = new FichaProducao();
        listaComponentes.forEach((comp) -> {
            fichaProducao.addComponente(comp);
        });
        return fichaProducao;
    }
}
