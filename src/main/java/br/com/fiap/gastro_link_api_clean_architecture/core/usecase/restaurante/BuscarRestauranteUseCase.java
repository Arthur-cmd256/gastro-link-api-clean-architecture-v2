package br.com.fiap.gastro_link_api_clean_architecture.core.usecase.restaurante;


import br.com.fiap.gastro_link_api_clean_architecture.core.domain.Restaurante;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.IRestauranteGateway;

public class BuscarRestauranteUseCase {

    private final IRestauranteGateway restauranteGateway;

    private BuscarRestauranteUseCase(IRestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    public static BuscarRestauranteUseCase criar(IRestauranteGateway restauranteGateway) {
        return new BuscarRestauranteUseCase(restauranteGateway);
    }

    public Restaurante processar(Long idRestaurante) {
        return this.restauranteGateway
                .buscarRestaurantePorId(idRestaurante)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado com o ID: " + idRestaurante));
    }
}
