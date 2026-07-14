package br.com.fiap.gastro_link_api_clean_architecture.core.exception;

public class ItemCardapioNaoEncontradoException extends EntidadeNaoEncotradaException {
    public ItemCardapioNaoEncontradoException(Long id) {
        super("ItemCardapio", id);
    }
}
