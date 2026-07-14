package br.com.fiap.gastro_link_api_clean_architecture.infra.database.mapper;


import br.com.fiap.gastro_link_api_clean_architecture.core.domain.TipoUsuario;
import br.com.fiap.gastro_link_api_clean_architecture.infra.database.jpa.entity.TipoUsuarioJpaEntity;

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
}

