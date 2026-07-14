package br.com.fiap.gastro_link_api_clean_architecture.core.usecase.tipousuario;


import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.ITipoUsuarioGateway;

public class DeletarTipoUsuarioUseCase {
    private final ITipoUsuarioGateway tipoUsuarioGateway;

    private DeletarTipoUsuarioUseCase(ITipoUsuarioGateway tipoUsuarioGateway) {
        this.tipoUsuarioGateway = tipoUsuarioGateway;
    }

    public static DeletarTipoUsuarioUseCase criar(ITipoUsuarioGateway tipoUsuarioGateway) {
        return new DeletarTipoUsuarioUseCase(tipoUsuarioGateway);
    }

    public void processar(Long id) {
        this.tipoUsuarioGateway.deletarTipoUsuario(id);
    }
}
