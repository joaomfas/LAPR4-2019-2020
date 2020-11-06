package eapli.base.smm.server;

import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import eapli.base.gestaomaquinas.domain.Maquina;
import eapli.base.gestaomaquinas.domain.NumeroIdentificacaoUnico;
import eapli.base.smm.application.MonitorizarLinhaRunnable;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SmmServer {
    
    public static final int PORTA = 9999;
    
    /**
     * Static Map [linhasProducao -> ThreadLinha]
     */
    private static Map<LinhaProducao, MonitorizarLinhaRunnable> linhasProducao = new HashMap();
    
    private static Map<Integer ,InetAddress> maquinaIps = new HashMap();
    
    public SmmServer() {
    }
    
    /**
     * Guarda e inicia Thread de uma linha
     * @param linhaProducao
     * @return 
     */
    public boolean monitorizarLinhaProducao(LinhaProducao linhaProducao) {
        if(!linhasProducao.keySet().contains(linhaProducao)) {
            MonitorizarLinhaRunnable mlp = new MonitorizarLinhaRunnable(linhaProducao);
            linhasProducao.put(linhaProducao, mlp);
            Thread t = new Thread(linhasProducao.get(linhaProducao));
            t.start();
            return true;
        }
        return false;
    }
    
    /**
     * Função retorna a class Thread da Linha
     * @param linhaProducao
     * @return 
     */
    public MonitorizarLinhaRunnable linhaProducaoRunnable(LinhaProducao linhaProducao) {        
        if(linhasProducao.containsKey(linhaProducao))
            return linhasProducao.get(linhaProducao);
        return null;
    }
    
    /**
     * Linhas de produção monitorizadas.
     * @return 
     */
    public Set<LinhaProducao> linhasProducao() {
        return linhasProducao.keySet();
    }
    
    /**
     * Associa IP a Maquina
     * @param ip
     * @param niu 
     */
    public boolean bindIp(InetAddress ip, int niu) {
        if(maquinaIps.containsKey(niu) && !maquinaIps.get(niu).equals(ip))  {
            System.out.println("Erro! Mesmo número de identificação único atribuido a vários ips");
            return false;
        } 
        NumeroIdentificacaoUnico codigoUnico = new NumeroIdentificacaoUnico(String.valueOf(niu));
        Set<LinhaProducao> lp = linhasProducao();       
        for(LinhaProducao l : lp) {
            if(l.contemMaquinaComNumUnico(codigoUnico)) {
                Maquina maq = l.maquina(codigoUnico);
                maq.especificarIP(ip);
                maq.update();
                maquinaIps.put(niu, ip);
                return true;
            }
        }
        return false;
    }
    
}
