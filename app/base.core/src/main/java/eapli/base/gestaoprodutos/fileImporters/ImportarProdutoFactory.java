package eapli.base.gestaoprodutos.fileImporters;

public class ImportarProdutoFactory {
    
    public final IProdutoImporter build(TipoFicheiroProdutoImporter tipo){
        
        switch (tipo) {
        case CSV:
            return new CSVProdutoImporter();
        }
        throw new IllegalStateException("Formato desconhecido!");
    }
}
