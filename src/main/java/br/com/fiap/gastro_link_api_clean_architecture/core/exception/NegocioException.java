package br.com.fiap.gastro_link_api_clean_architecture.core.exception;

public class NegocioException extends RuntimeException {
    public NegocioException(String message) {
        super(message);
    }
}
