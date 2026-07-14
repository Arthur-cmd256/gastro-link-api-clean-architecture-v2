package br.com.fiap.gastro_link_api_clean_architecture.core.usecase.tipousuario;


import br.com.fiap.gastro_link_api_clean_architecture.core.domain.TipoUsuario;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.ITipoUsuarioGateway;

public class CadastrarTipoUsuarioUseCase {

    private final ITipoUsuarioGateway tipoUsuarioGateway;

    private CadastrarTipoUsuarioUseCase(ITipoUsuarioGateway tipoUsuarioGateway) {
        this.tipoUsuarioGateway = tipoUsuarioGateway;
    }

    public static CadastrarTipoUsuarioUseCase criar(ITipoUsuarioGateway tipoUsuarioGateway) {
        return new CadastrarTipoUsuarioUseCase(tipoUsuarioGateway);
    }

    public TipoUsuario processar(String nome) {
        TipoUsuario tipoUsuario = TipoUsuario.criar(nome);
        return this.tipoUsuarioGateway.salvarTipoUsuario(tipoUsuario);
    }
}
