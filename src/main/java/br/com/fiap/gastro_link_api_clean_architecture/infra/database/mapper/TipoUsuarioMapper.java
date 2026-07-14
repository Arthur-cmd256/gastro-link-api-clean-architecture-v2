package br.com.fiap.gastro_link_api_clean_architecture.infra.database.mapper;


import br.com.fiap.gastro_link_api_clean_architecture.core.domain.TipoUsuario;
import br.com.fiap.gastro_link_api_clean_architecture.core.dto.CadastrarTipoUsuarioInputDTO;
import br.com.fiap.gastro_link_api_clean_architecture.core.dto.CadastrarTipoUsuarioOutDTO;
import br.com.fiap.gastro_link_api_clean_architecture.infra.database.jpa.entity.TipoUsuarioJpaEntity;
import br.com.fiap.gastro_link_api_clean_architecture.infra.web.request.CriarTipoUsuarioRequest;
import br.com.fiap.gastro_link_api_clean_architecture.infra.web.response.CriarTipoUsuarioResponse;

public class TipoUsuarioMapper {

    public static TipoUsuarioJpaEntity toJpaEntity(TipoUsuario tipoUsuario) {
        if (tipoUsuario == null) return null;

        TipoUsuarioJpaEntity jpaEntity = new TipoUsuarioJpaEntity();
        jpaEntity.setId(tipoUsuario.getId());
        jpaEntity.setNome(tipoUsuario.getNome());

        return jpaEntity;
    }

    public static TipoUsuario toDomain(TipoUsuarioJpaEntity jpaEntity) {
        if (jpaEntity == null) return null;

        return TipoUsuario.criar(jpaEntity.getId(), jpaEntity.getNome());
    }

    public static CadastrarTipoUsuarioInputDTO toCadastrarTipoUsuarioInputDTO(CriarTipoUsuarioRequest criarTipoUsuarioRequest) {
        if (criarTipoUsuarioRequest == null) return null;

        return new CadastrarTipoUsuarioInputDTO(
                criarTipoUsuarioRequest.nome()
        );
    }

    public static CriarTipoUsuarioResponse toCriarTipoUsuarioResponse(CadastrarTipoUsuarioOutDTO outDTO) {
        if (outDTO == null) return null;

        return new CriarTipoUsuarioResponse(
                outDTO.id(),
                outDTO.nome()
        );
    }

    public static br.com.fiap.gastro_link_api_clean_architecture.core.dto.AtualizarTipoUsuarioInputDTO toAtualizarTipoUsuarioInputDTO(Long id, CriarTipoUsuarioRequest criarTipoUsuarioRequest) {
        if (criarTipoUsuarioRequest == null) return null;

        return new br.com.fiap.gastro_link_api_clean_architecture.core.dto.AtualizarTipoUsuarioInputDTO(
                id,
                criarTipoUsuarioRequest.nome()
        );
    }
}

