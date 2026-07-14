package br.com.fiap.gastro_link_api_clean_architecture.core.exception;

public class RestauranteDeveTerDonoException extends NegocioException {
    public RestauranteDeveTerDonoException() {
        super("Todo restaurante deve estar associado a um usuário proprietário.");
    }
}