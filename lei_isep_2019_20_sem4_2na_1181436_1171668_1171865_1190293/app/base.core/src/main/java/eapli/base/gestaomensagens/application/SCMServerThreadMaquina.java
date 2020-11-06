package eapli.base.gestaomensagens.application;

import eapli.base.gestaolinhasproducao.domain.LinhaProducao;
import eapli.base.gestaomaquinas.domain.Maquina;
import eapli.base.gestaomaquinas.domain.MaquinaConfigFile;
import eapli.base.gestaomaquinas.domain.NumeroIdentificacaoUnico;
import eapli.base.gestaomaquinas.repositories.MaquinaRepository;
import eapli.base.gestaomensagens.dominio.Codes;
import eapli.base.gestaomensagens.dominio.Mensagem;
import eapli.base.gestaomensagens.repositories.MensagemRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class SCMServerThreadMaquina implements Runnable {

    private LigacaoTCPMaquina ligacao;
    private MaquinaRepository maqRepository = PersistenceContext.repositories().maquinas();
    private MensagemRepository msgRepository = PersistenceContext.repositories().mensagens();
    private LinhaProducao linhaProducao;
    private Maquina mq = null;

    public SCMServerThreadMaquina(LigacaoTCPMaquina ligacao) {
        this.ligacao = ligacao;
    }

    @Override
    public void run() {
        try {
            Mensagem msg;

            if (!verificarMensagemHello()) {
                ligacao.desligarLigacao();
                throw new IllegalStateException("Mensagem HELLO inválida!");
            }

            System.out.println("Nova ligação da máquina " + ligacao.clientIP().getHostAddress()
                    + ", port number " + ligacao.porta() + ". Linha de producao: " + linhaProducao.identity());

            if (((ArrayList<MaquinaConfigFile>) maqRepository.fichParaEnvioDeMaquina(mq.numeroIdentificacaoUnico())).size() > 0 && !mq.getFichParaEnvio().equals("")) {
                MaquinaConfigFile file = ((ArrayList<MaquinaConfigFile>) maqRepository.fichParaEnvioDeMaquina(mq.numeroIdentificacaoUnico())).get(0);
                mq.setFichParaEnvio("");
                maqRepository.save(mq);
                ligacao.enviarConfig(file.fichConfigMaquina(), mq.numeroIdentificacaoUnico());
                msg = ligacao.receberMensagem();
                if (msg.code() == Codes.ACK.getCode()) {
                    System.out.println("Ficheiro CONFIG entregue e aceite!");
                } else {
                    System.out.println("Ficheiro CONFIG entregue mas rejeitado!");
                }
            } else {
                ligacao.enviarAck();
                do {
                    msg = ligacao.receberMensagem();
                    if (msg == null) {
                        break;
                    }

                    if (!validarMensagem(msg)) {
                        ligacao.desligarLigacao();
                    }

                    msg.updateDataGeracao(new Date());
                    msg.updateDataRecepcao(new Date());
                    msgRepository.save(msg);
                    ligacao.enviarAck();
                    System.out.println("Recebida mensagem da máquina " + mq.codInterno().codInterno() + ": "
                            + msg.rawData().toString());

                } while (true);
            }
            ligacao.desligarLigacao();
            System.out.println("Máquina " + ligacao.clientIP().getHostAddress()
                    + ", port number: " + ligacao.porta() + " descontectou");
        } catch (Exception ex) {
            try {
                ligacao.desligarLigacao();
                System.out.println("Máquina " + ligacao.clientIP().getHostAddress()
                        + ", port number: " + ligacao.porta() + " descontectou");
            } catch (Exception ex1) {
            }
        }
    }

    private boolean validarMensagem(Mensagem msg) throws IOException {
        //Se a mensagem não for do tipo MSG envia NACK e encerra.
        if (!msg.isTypeMSG()) {
            ligacao.enviarNack("Mensagem não é do tipo MSG");
            return false;
        }

        //Se o ID não for válido ou o IP não corresponder ao registo envia NACK e encerra
        if (!mq.validaIdentidadeMensagem(ligacao.clientIP(), mq.numeroIdentificacaoUnico())) {
            ligacao.enviarNack("IP de origem não corresponde à máquina indicada");
            return false;
        }
        return true;
    }

    private boolean verificarMensagemHello() throws IOException, Exception {
        Mensagem msg = ligacao.receberMensagem();
        if (msg == null) {
            ligacao.desligarLigacao();
            return false;
        }
        //Mensagem valida a versão de codificação e o código da mensagem
        try {
            msg.validaMensagemHello();
        } catch (Exception e) {
            ligacao.enviarNack(e.getMessage());
            return false;
        }
        //Verifica se é uma máquina existente na linha em monitorização
        boolean maqValida = linhaProducao.contemMaquinaComNumUnico(new NumeroIdentificacaoUnico(String.valueOf(msg.id())));
        if (!maqValida) {
            ligacao.enviarNack("Máquina não pertencente a esta linha de produção");
            return false;
        }
        Iterable<Maquina> maqs = maqRepository.maquinaComCodigoUnico(new NumeroIdentificacaoUnico(String.valueOf(msg.id())));
        for (Maquina m : maqs) {
            mq = m;
        }
        //Atualiza o endereço IP da máquina
        mq.especificarIP(ligacao.clientIP());
        maqRepository.save(mq);

        return true;
    }

    public void setLinhaProducao(LinhaProducao linhaProducao) {
        this.linhaProducao = linhaProducao;
    }
}
