/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.base.persistence.impl.jpa;

import eapli.base.gestaomaquinas.domain.CodInterno;
import eapli.base.gestaomaquinas.domain.Maquina;
import eapli.base.gestaomaquinas.domain.MaquinaConfigFile;
import eapli.base.gestaomaquinas.domain.NumeroIdentificacaoUnico;
import eapli.base.gestaomaquinas.repositories.MaquinaRepository;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.TypedQuery;

/**
 *
 * @author 35193
 */
public class JpaMaquinaRepository extends BaseJpaRepositoryBase<Maquina, CodInterno, CodInterno> implements MaquinaRepository{
    
    public JpaMaquinaRepository() {
        super("codInterno");
    }

    @Override
    public Iterable<Maquina> maquinaComCodigoUnico(NumeroIdentificacaoUnico codigoUnico) {
        final Map<String, Object> params = new HashMap<>();
        params.put("numeroIdentificacaoUnico", codigoUnico);
        return match ("e.numeroIdentificacaoUnico = :numeroIdentificacaoUnico", params);
    }

    @Override
    public Iterable<MaquinaConfigFile> fichParaEnvioDeMaquina(NumeroIdentificacaoUnico codigoUnico) {
        final TypedQuery<MaquinaConfigFile> query = entityManager().createQuery(
                "SELECT aind FROM Maquina j JOIN j.ficheirosConfiguracao aind "
                        + "WHERE aind.marcarParaEnvio = true "
                        + "AND j.numeroIdentificacaoUnico = :nui",
                MaquinaConfigFile.class);
        
        query.setParameter("nui", codigoUnico);

        return query.getResultList();
    }
    
    
}
