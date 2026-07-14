package br.com.fiap.gastro_link_api_clean_architecture.core.exception;

public class TipoUsuarioNaoEncontradoException extends EntidadeNaoEncotradaException {
    public TipoUsuarioNaoEncontradoException(Long id) {
        super("TipoUsuario", id);
    }
}
