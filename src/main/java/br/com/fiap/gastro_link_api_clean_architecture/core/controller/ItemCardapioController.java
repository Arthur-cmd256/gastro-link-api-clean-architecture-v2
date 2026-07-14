package br.com.fiap.gastro_link_api_clean_architecture.core.controller;

import br.com.fiap.gastro_link_api_clean_architecture.core.domain.ItemCardapio;
import br.com.fiap.gastro_link_api_clean_architecture.core.dto.CadastrarItemCardapioInput;
import br.com.fiap.gastro_link_api_clean_architecture.core.dto.AtualizarItemCardapioInput;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.IItemCardapioGateway;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.IRestauranteGateway;
import br.com.fiap.gastro_link_api_clean_architecture.core.usecase.itemcardapio.CadastrarItemCardapioUseCase;
import br.com.fiap.gastro_link_api_clean_architecture.core.usecase.itemcardapio.AtualizarItemCardapioUseCase;
import br.com.fiap.gastro_link_api_clean_architecture.core.usecase.itemcardapio.BuscarItensCardapioPorRestauranteUseCase;
import br.com.fiap.gastro_link_api_clean_architecture.core.usecase.itemcardapio.DeletarItemCardapioUseCase;

import java.util.List;

public class ItemCardapioController {

    private final IItemCardapioGateway itemCardapioGateway;
    private final IRestauranteGateway restauranteGateway;

    private ItemCardapioController(IItemCardapioGateway itemCardapioGateway, IRestauranteGateway restauranteGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
        this.restauranteGateway = restauranteGateway;
    }

    public static ItemCardapioController criar(IItemCardapioGateway itemCardapioGateway, IRestauranteGateway restauranteGateway) {
        return new ItemCardapioController(itemCardapioGateway, restauranteGateway);
    }

    public ItemCardapio cadastrar(CadastrarItemCardapioInput cadastrarItemCardapioInput) {
        return CadastrarItemCardapioUseCase.criar(this.itemCardapioGateway, this.restauranteGateway).processar(cadastrarItemCardapioInput);
    }

    public ItemCardapio atualizar(AtualizarItemCardapioInput atualizarItemCardapioInput) {
        return AtualizarItemCardapioUseCase.criar(this.itemCardapioGateway).processar(atualizarItemCardapioInput);
    }

    public List<ItemCardapio> buscarPorRestaurante(Long restauranteId) {
        return BuscarItensCardapioPorRestauranteUseCase.criar(this.itemCardapioGateway).processar(restauranteId);
    }

    public void deletar(Long idItemCardapio) {
        DeletarItemCardapioUseCase.criar(this.itemCardapioGateway).processar(idItemCardapio);
    }
}
