package br.com.fiap.gastro_link_api_clean_architecture.infra.database.jpa.repository;

import br.com.fiap.gastro_link_api_clean_architecture.infra.database.jpa.entity.RestauranteJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringRestauranteRepository extends JpaRepository<RestauranteJpaEntity, Long> {
}
