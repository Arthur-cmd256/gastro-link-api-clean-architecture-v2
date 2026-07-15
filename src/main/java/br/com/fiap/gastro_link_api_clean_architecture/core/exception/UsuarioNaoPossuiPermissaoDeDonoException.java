package br.com.fiap.gastro_link_api_clean_architecture.core.exception;

public class UsuarioNaoPossuiPermissaoDeDonoException extends NegocioException {
    public UsuarioNaoPossuiPermissaoDeDonoException() {
        super("Para realizar essa operação o usuario precisa ter permissão de dono");
    }
}
