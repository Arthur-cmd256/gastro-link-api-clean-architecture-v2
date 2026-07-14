package br.com.fiap.gastro_link_api_clean_architecture.core.usecase.itemcardapio;


import br.com.fiap.gastro_link_api_clean_architecture.core.domain.ItemCardapio;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.IItemCardapioGateway;

import java.util.List;

public class BuscarItensCardapioPorRestauranteUseCase {
    private final IItemCardapioGateway itemCardapioGateway;

    private BuscarItensCardapioPorRestauranteUseCase(IItemCardapioGateway itemCardapioGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
    }

    public static BuscarItensCardapioPorRestauranteUseCase criar(IItemCardapioGateway itemCardapioGateway) {
        return new BuscarItensCardapioPorRestauranteUseCase(itemCardapioGateway);
    }

    public List<ItemCardapio> processar(Long idRestaurante) {
        return this.itemCardapioGateway.buscarItensCardapioPorRestauranteId(idRestaurante);
    }
}
