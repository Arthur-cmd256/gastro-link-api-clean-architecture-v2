package br.com.fiap.gastro_link_api_clean_architecture.core.gateway;



import br.com.fiap.gastro_link_api_clean_architecture.core.domain.Restaurante;

import java.util.Optional;

public interface IRestauranteGateway {
    Restaurante salvarRestaurante(Restaurante restaurante);

    Optional<Restaurante> buscarRestaurantePorId(Long idRestaurante);

    void deletarRestaurante(Long idRestaurante);
}
