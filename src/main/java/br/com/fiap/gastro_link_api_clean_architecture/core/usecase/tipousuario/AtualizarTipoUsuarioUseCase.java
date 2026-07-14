package br.com.fiap.gastro_link_api_clean_architecture.core.usecase.tipousuario;


import br.com.fiap.gastro_link_api_clean_architecture.core.domain.TipoUsuario;
import br.com.fiap.gastro_link_api_clean_architecture.core.dto.AtualizarTipoUsuarioInput;
import br.com.fiap.gastro_link_api_clean_architecture.core.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.ITipoUsuarioGateway;

public class AtualizarTipoUsuarioUseCase {

    private final ITipoUsuarioGateway tipoUsuarioGateway;

    private AtualizarTipoUsuarioUseCase(ITipoUsuarioGateway tipoUsuarioGateway) {
        this.tipoUsuarioGateway = tipoUsuarioGateway;
    }

    public static AtualizarTipoUsuarioUseCase criar(ITipoUsuarioGateway tipoUsuarioGateway) {
        return new AtualizarTipoUsuarioUseCase(tipoUsuarioGateway);
    }

    public TipoUsuario processar(AtualizarTipoUsuarioInput atualizarTipoUsuarioInput) {
        Long idTipoUsuario = atualizarTipoUsuarioInput.id();
        TipoUsuario tipoUsuario = this.tipoUsuarioGateway
                .buscarTipoUsuarioPorId(idTipoUsuario)
                .orElseThrow(() -> new TipoUsuarioNaoEncontradoException(idTipoUsuario));
        TipoUsuario tipoUsuarioAlterado = TipoUsuario.criar(
                atualizarTipoUsuarioInput.id(),
                atualizarTipoUsuarioInput.nome()
        );
        return this.tipoUsuarioGateway.salvarTipoUsuario(tipoUsuarioAlterado);
    }
}
