package br.com.fiap.gastro_link_api_clean_architecture.core.usecase.restaurante;


import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.IRestauranteGateway;

public class DeletarRestauranteUseCase {
    private final IRestauranteGateway restauranteGateway;

    private DeletarRestauranteUseCase(IRestauranteGateway restauranteGateway) {
        this.restauranteGateway = restauranteGateway;
    }

    public static DeletarRestauranteUseCase criar(IRestauranteGateway restauranteGateway) {
        return new DeletarRestauranteUseCase(restauranteGateway);
    }

    public void processar(Long idRestaurante) {
        this.restauranteGateway.deletarRestaurante(idRestaurante);
    }
}
