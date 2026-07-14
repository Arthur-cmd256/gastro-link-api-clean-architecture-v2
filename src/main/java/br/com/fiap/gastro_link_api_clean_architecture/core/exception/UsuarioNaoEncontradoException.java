package br.com.fiap.gastro_link_api_clean_architecture.core.exception;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncotradaException {
    public UsuarioNaoEncontradoException(Long id) {
        super("Usuario", id);
    }
}
