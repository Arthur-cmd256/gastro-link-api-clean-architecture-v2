package br.com.fiap.gastro_link_api_clean_architecture.core.factory;


import br.com.fiap.gastro_link_api_clean_architecture.core.domain.ItemCardapio;
import br.com.fiap.gastro_link_api_clean_architecture.core.domain.Restaurante;

import java.math.BigDecimal;

public class ItemCardapioFactory {

    public static ItemCardapio criarItemCardapioPadrao() {
        String nomeItemCardapio = "nomeItemCardapio";
        String descricaoItemCardapio = "descricaoItemCardapio";
        BigDecimal precoItemCardapio = BigDecimal.valueOf(35.99);
        Boolean disponibilidadeApenasRestauranteItemCardapio = Boolean.FALSE;
        String caminhoFotoItemCardapio = "fotoItemCardapio";
        Restaurante restaurantePadrao = RestauranteFactory.criarRestaurantePadrao();

        return ItemCardapio.criar(
                30L,
                nomeItemCardapio,
                descricaoItemCardapio,
                precoItemCardapio,
                disponibilidadeApenasRestauranteItemCardapio,
                caminhoFotoItemCardapio,
                restaurantePadrao
        );
    }

    public static ItemCardapio criarItemCardapioComIdRestaurante(Long idRestaurante) {
        String nomeItemCardapio = "nomeItemCardapio";
        String descricaoItemCardapio = "descricaoItemCardapio";
        BigDecimal precoItemCardapio = BigDecimal.valueOf(35.99);
        Boolean disponibilidadeApenasRestauranteItemCardapio = Boolean.FALSE;
        String caminhoFotoItemCardapio = "fotoItemCardapio";
        Restaurante restaurantePadrao = RestauranteFactory.criarRestauranteComId(idRestaurante);

        return ItemCardapio.criar(
                30L,
                nomeItemCardapio,
                descricaoItemCardapio,
                precoItemCardapio,
                disponibilidadeApenasRestauranteItemCardapio,
                caminhoFotoItemCardapio,
                restaurantePadrao
        );
    }
}
