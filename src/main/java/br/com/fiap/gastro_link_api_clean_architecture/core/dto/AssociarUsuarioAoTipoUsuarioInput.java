package br.com.fiap.gastro_link_api_clean_architecture.core.dto;

public record AssociarUsuarioAoTipoUsuarioInput(
        Long idUsuario,
        Long idTipoUsuario
){
}
