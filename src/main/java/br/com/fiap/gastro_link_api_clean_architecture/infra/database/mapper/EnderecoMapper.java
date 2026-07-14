package br.com.fiap.gastro_link_api_clean_architecture.infra.database.mapper;


import br.com.fiap.gastro_link_api_clean_architecture.core.domain.Endereco;
import br.com.fiap.gastro_link_api_clean_architecture.infra.database.jpa.entity.EnderecoJpaEntity;

public class EnderecoMapper {
    public static EnderecoJpaEntity toJpaEntity(Endereco endereco) {
        EnderecoJpaEntity jpaEntity = new EnderecoJpaEntity();
        jpaEntity.setId(endereco.getId());
        jpaEntity.setLogradouro(endereco.getLogradouro());
        jpaEntity.setNumero(endereco.getNumero());
        jpaEntity.setComplemento(endereco.getComplemento());
        jpaEntity.setBairro(endereco.getBairro());
        jpaEntity.setCidade(endereco.getCidade());
        jpaEntity.setUf(endereco.getUf());
        jpaEntity.setCep(endereco.getCep());
        return jpaEntity;
    }

    public static Endereco toDomain(EnderecoJpaEntity jpaEntity) {
        return Endereco.criar(
            jpaEntity.getLogradouro(),
            jpaEntity.getComplemento(),
            jpaEntity.getNumero(),
            jpaEntity.getBairro(),
            jpaEntity.getCidade(),
            jpaEntity.getUf(),
            jpaEntity.getCep()
        );
    }
}
