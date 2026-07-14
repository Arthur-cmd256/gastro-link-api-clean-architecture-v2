package br.com.fiap.gastro_link_api_clean_architecture.infra.database.jpa.repository;


import br.com.fiap.gastro_link_api_clean_architecture.infra.database.jpa.entity.EnderecoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringEnderecoRepository extends JpaRepository<EnderecoJpaEntity, Long> {
}

