package br.com.fiap.gastro_link_api_clean_architecture.core.usecase.itemcardapio;

import br.com.fiap.gastro_link_api_clean_architecture.core.domain.ItemCardapio;
import br.com.fiap.gastro_link_api_clean_architecture.core.dto.AtualizarItemCardapioInput;
import br.com.fiap.gastro_link_api_clean_architecture.core.exception.ItemCardapioNaoEncontradoException;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.IItemCardapioGateway;

public class AtualizarItemCardapioUseCase {

    IItemCardapioGateway itemCardapioGateway;

    private AtualizarItemCardapioUseCase(IItemCardapioGateway itemCardapioGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
    }

    public static AtualizarItemCardapioUseCase criar(IItemCardapioGateway itemCardapioGateway) {
        return new AtualizarItemCardapioUseCase(itemCardapioGateway);
    }

    public ItemCardapio processar(AtualizarItemCardapioInput atualizarItemCardapioInput) {
        ItemCardapio itemCardapio = this.itemCardapioGateway
                .buscarItemCardapioPorId(atualizarItemCardapioInput.id()).orElseThrow(() -> new ItemCardapioNaoEncontradoException(atualizarItemCardapioInput.id()));
        ItemCardapio itemCardapioAtualizacao = ItemCardapio.criar(
                atualizarItemCardapioInput.id(),
                atualizarItemCardapioInput.nome(),
                atualizarItemCardapioInput.descricao(),
                atualizarItemCardapioInput.preco(),
                atualizarItemCardapioInput.disponibilidadeApenasRestaurante(),
                atualizarItemCardapioInput.caminhoFoto(),
                itemCardapio.getRestaurante()
        );
        return this.itemCardapioGateway.salvarItemCardapio(itemCardapioAtualizacao);
    }
}
