package br.com.fiap.gastro_link_api_clean_architecture.core.gateway;

import br.com.fiap.gastro_link_api_clean_architecture.core.domain.Usuario;

import java.util.Optional;

public interface IUsuarioGateway {
    Optional<Usuario> buscarUsuarioPorId(Long idUsuario);

    Usuario salvarUsuario(Usuario usuario);
}
