package eapli.base.gestaomensagens.protocolo;

import eapli.base.gestaomensagens.dominio.Mensagem;

public interface IDescodificar {
    Mensagem descodificarMensagem(byte[] data);
}
