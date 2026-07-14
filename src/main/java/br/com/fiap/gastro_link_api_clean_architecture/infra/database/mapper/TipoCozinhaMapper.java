package br.com.fiap.gastro_link_api_clean_architecture.infra.database.mapper;


import br.com.fiap.gastro_link_api_clean_architecture.core.domain.TipoCozinha;
import br.com.fiap.gastro_link_api_clean_architecture.infra.database.jpa.entity.TipoCozinhaJpaEntity;

public class TipoCozinhaMapper {

    public static TipoCozinhaJpaEntity toJpaEntity(TipoCozinha tipoCozinha) {
        if (tipoCozinha == null) return null;

        TipoCozinhaJpaEntity jpaEntity = new TipoCozinhaJpaEntity();
        jpaEntity.setId(tipoCozinha.getId());
        jpaEntity.setNome(tipoCozinha.getNome());

        return jpaEntity;
    }

    public static TipoCozinha toDomain(TipoCozinhaJpaEntity jpaEntity) {
        if (jpaEntity == null) return null;

        return TipoCozinha.criar(jpaEntity.getId(), jpaEntity.getNome());
    }
}
