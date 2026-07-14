package br.com.fiap.gastro_link_api_clean_architecture.core.exception;

public class DadoObrigatorioException extends NegocioException {
    public DadoObrigatorioException(String campo) {
        super(String.format("O campo '%s' é obrigatório.", campo));
    }
}