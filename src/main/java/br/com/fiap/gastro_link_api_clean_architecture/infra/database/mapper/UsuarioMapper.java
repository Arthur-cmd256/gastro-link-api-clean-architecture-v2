package br.com.fiap.gastro_link_api_clean_architecture.infra.database.mapper;


import br.com.fiap.gastro_link_api_clean_architecture.core.domain.Usuario;
import br.com.fiap.gastro_link_api_clean_architecture.infra.database.jpa.entity.UsuarioJpaEntity;

public class UsuarioMapper {

    public static UsuarioJpaEntity toJpaEntity(Usuario usuario) {
        if (usuario == null) return null;

        UsuarioJpaEntity jpaEntity = new UsuarioJpaEntity();
        jpaEntity.setId(usuario.getId());
        jpaEntity.setNome(usuario.getNome());
        jpaEntity.setEnderecoDeEmail(usuario.getEnderecoDeEmail());
        jpaEntity.setTipoUsuario(TipoUsuarioMapper.toJpaEntity(usuario.getTipoUsuario()));

        return jpaEntity;
    }

    public static Usuario toDomain(UsuarioJpaEntity jpaEntity) {
        if (jpaEntity == null) return null;

        return Usuario.criar(
                jpaEntity.getId(),
                jpaEntity.getNome(),
                jpaEntity.getEnderecoDeEmail(),
                TipoUsuarioMapper.toDomain(jpaEntity.getTipoUsuario())
        );
    }
}

