package br.com.fiap.gastro_link_api_clean_architecture.core.usecase.usuario;

import br.com.fiap.gastro_link_api_clean_architecture.core.domain.TipoUsuario;
import br.com.fiap.gastro_link_api_clean_architecture.core.domain.Usuario;
import br.com.fiap.gastro_link_api_clean_architecture.core.dto.AssociarUsuarioAoTipoUsuarioInput;
import br.com.fiap.gastro_link_api_clean_architecture.core.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.gastro_link_api_clean_architecture.core.exception.UsuarioNaoEncontradoException;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.ITipoUsuarioGateway;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.IUsuarioGateway;

public class AssociarUsuarioAoTipoUsuarioUseCase {
    private final ITipoUsuarioGateway tipoUsuarioGateway;
    private final IUsuarioGateway usuarioGateway;

    private AssociarUsuarioAoTipoUsuarioUseCase(ITipoUsuarioGateway tipoUsuarioGateway, IUsuarioGateway usuarioGateway) {
        this.tipoUsuarioGateway = tipoUsuarioGateway;
        this.usuarioGateway = usuarioGateway;
    }

    public static AssociarUsuarioAoTipoUsuarioUseCase criar(ITipoUsuarioGateway tipoUsuarioGateway,  IUsuarioGateway usuarioGateway) {
        return new AssociarUsuarioAoTipoUsuarioUseCase(tipoUsuarioGateway, usuarioGateway);
    }

    public Usuario processar(AssociarUsuarioAoTipoUsuarioInput associarUsuarioAoTipoUsuarioInput) {
        Long idUsuario = associarUsuarioAoTipoUsuarioInput.idUsuario();
        Long idTipoUsuario = associarUsuarioAoTipoUsuarioInput.idTipoUsuario();
        Usuario usuarioBuscado = this.usuarioGateway
                .buscarUsuarioPorId(idUsuario)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(idUsuario));
        TipoUsuario tipoUsuarioBuscado = this.tipoUsuarioGateway
                .buscarTipoUsuarioPorId(idTipoUsuario)
                .orElseThrow(() -> new TipoUsuarioNaoEncontradoException(idTipoUsuario));
        Usuario usuarioComTipo = Usuario.criar(
                usuarioBuscado.getId(),
                usuarioBuscado.getNome(),
                usuarioBuscado.getEnderecoDeEmail(),
                tipoUsuarioBuscado,
                usuarioBuscado.getPossuiPermissaoDeDono()
        );
        return this.usuarioGateway.salvarUsuario(usuarioComTipo);
    }
}
