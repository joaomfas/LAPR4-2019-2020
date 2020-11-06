package eapli.base.gestaomensagens.protocolo;

import eapli.base.gestaomensagens.dominio.Mensagem;

public interface ICodificar {
    byte[] codificarMensagem(Mensagem m);
}
