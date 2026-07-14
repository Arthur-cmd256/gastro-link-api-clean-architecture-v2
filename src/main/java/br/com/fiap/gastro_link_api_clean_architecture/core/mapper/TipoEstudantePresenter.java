package br.com.fiap.gastro_link_api_clean_architecture.core.mapper;

import br.com.fiap.gastro_link_api_clean_architecture.core.domain.TipoUsuario;
import br.com.fiap.gastro_link_api_clean_architecture.core.dto.CadastrarTipoUsuarioOutDTO;

public class TipoEstudantePresenter {

    public static CadastrarTipoUsuarioOutDTO mapToCadastrarTipoUsuarioOutDTO(TipoUsuario tipoUsuario) {
        return new CadastrarTipoUsuarioOutDTO(
                tipoUsuario.getId(),
                tipoUsuario.getNome()
        );
    }
}
