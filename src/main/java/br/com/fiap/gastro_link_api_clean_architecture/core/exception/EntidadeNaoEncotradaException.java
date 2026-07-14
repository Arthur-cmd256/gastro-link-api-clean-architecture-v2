package br.com.fiap.gastro_link_api_clean_architecture.core.exception;

public class EntidadeNaoEncotradaException extends NegocioException {
    public EntidadeNaoEncotradaException(String entidade, Long id) {
        super(String.format("%s com ID %d não foi encontrado.", entidade, id));
    }
}
