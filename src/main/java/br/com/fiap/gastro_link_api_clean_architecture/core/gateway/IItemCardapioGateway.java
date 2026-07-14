package br.com.fiap.gastro_link_api_clean_architecture.core.gateway;


import br.com.fiap.gastro_link_api_clean_architecture.core.domain.ItemCardapio;

import java.util.List;
import java.util.Optional;

public interface IItemCardapioGateway {
    ItemCardapio salvarItemCardapio(ItemCardapio itemCardapio);

    List<ItemCardapio> buscarItensCardapioPorRestauranteId(Long idRestaurante);

    Optional<ItemCardapio> buscarItemCardapioPorId(Long id);

    void deletarItemCardapio(Long idItemCardapio);
}
