package br.com.fiap.gastro_link_api_clean_architecture.core.gateway;


import br.com.fiap.gastro_link_api_clean_architecture.core.domain.TipoUsuario;

import java.util.Optional;

public interface ITipoUsuarioGateway {
    TipoUsuario salvarTipoUsuario(TipoUsuario tipoUsuario);

    Optional<TipoUsuario> buscarTipoUsuarioPorId(Long id);

    TipoUsuario deletarTipoUsuario(Long id);
}
