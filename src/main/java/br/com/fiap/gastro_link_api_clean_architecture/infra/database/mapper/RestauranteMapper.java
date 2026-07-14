package br.com.fiap.gastro_link_api_clean_architecture.infra.database.mapper;


import br.com.fiap.gastro_link_api_clean_architecture.core.domain.Restaurante;
import br.com.fiap.gastro_link_api_clean_architecture.infra.database.jpa.entity.RestauranteJpaEntity;

public class RestauranteMapper {

    public static RestauranteJpaEntity toJpaEntity(Restaurante restaurante) {
        if (restaurante == null) return null;

        RestauranteJpaEntity jpaEntity = new RestauranteJpaEntity();
        jpaEntity.setId(restaurante.getId());
        jpaEntity.setNome(restaurante.getNome());
        jpaEntity.setHorarioFuncionamento(restaurante.getHorarioFuncionamento());
        jpaEntity.setEnderecoCompleto(EnderecoMapper.toJpaEntity(restaurante.getEndereco()));
        jpaEntity.setDono(UsuarioMapper.toJpaEntity(restaurante.getDono()));
        jpaEntity.setTipoCozinha(TipoCozinhaMapper.toJpaEntity(restaurante.getTipoCozinha()));

        return jpaEntity;
    }

    public static Restaurante toDomain(RestauranteJpaEntity restauranteJpaEntity) {
        if (restauranteJpaEntity == null) return null;

        return Restaurante.criar(
                restauranteJpaEntity.getId(),
                restauranteJpaEntity.getNome(),
                EnderecoMapper.toDomain(restauranteJpaEntity.getEnderecoCompleto()),
                TipoCozinhaMapper.toDomain(restauranteJpaEntity.getTipoCozinha()),
                restauranteJpaEntity.getHorarioFuncionamento(),
                UsuarioMapper.toDomain(restauranteJpaEntity.getDono())
        );
    }

}
