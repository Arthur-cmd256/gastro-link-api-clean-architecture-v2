package br.com.fiap.gastro_link_api_clean_architecture.infra.database.jpa;

import br.com.fiap.gastro_link_api_clean_architecture.core.domain.Restaurante;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.IRestauranteGateway;
import br.com.fiap.gastro_link_api_clean_architecture.infra.database.jpa.entity.RestauranteJpaEntity;
import br.com.fiap.gastro_link_api_clean_architecture.infra.database.jpa.repository.SpringRestauranteRepository;
import br.com.fiap.gastro_link_api_clean_architecture.infra.database.mapper.RestauranteMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RestauranteJpaGateway implements IRestauranteGateway {
    private final SpringRestauranteRepository springRepository;

    public RestauranteJpaGateway(SpringRestauranteRepository springRepository) {
        this.springRepository = springRepository;
    }

    @Override
    public Restaurante salvarRestaurante(Restaurante restaurante) {
        RestauranteJpaEntity jpaEntity = RestauranteMapper.toJpaEntity(restaurante);
        RestauranteJpaEntity entitySalva = springRepository.save(jpaEntity);
        return RestauranteMapper.toDomain(entitySalva);
    }

    @Override
    public Optional<Restaurante> buscarRestaurantePorId(Long idRestaurante) {
         return springRepository.findById(idRestaurante).map(RestauranteMapper::toDomain);
    }

    @Override
    public void deletarRestaurante(Long idRestaurante) {
        springRepository.deleteById(idRestaurante);
    }
}
