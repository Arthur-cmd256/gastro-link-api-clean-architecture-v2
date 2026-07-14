package br.com.fiap.gastro_link_api_clean_architecture.core.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncotradaException {
    public RestauranteNaoEncontradoException(Long id) {
        super("Restaurante", id);
    }
}
