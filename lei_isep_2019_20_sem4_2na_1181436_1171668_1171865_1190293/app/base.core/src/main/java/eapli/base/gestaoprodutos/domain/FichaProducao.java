package eapli.base.gestaoprodutos.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;

@Embeddable
public class FichaProducao implements ValueObject {

    private static final long serialVersionUID = 1L;
    

    @ElementCollection
    private final Set<Componente> listaComponentes;

    public FichaProducao() {
        listaComponentes = new HashSet<>();
    }

    public final boolean addComponente(Componente componente){
        requisitosComponente(componente);
        return listaComponentes.add(componente);
    }
    
    private void requisitosComponente(Componente componente){
        Preconditions.nonNull(componente, "Componente não poder ser nulo!");
    }
    
    public Set<Componente> listaComponentes() {
        return listaComponentes;
    }
    
}
