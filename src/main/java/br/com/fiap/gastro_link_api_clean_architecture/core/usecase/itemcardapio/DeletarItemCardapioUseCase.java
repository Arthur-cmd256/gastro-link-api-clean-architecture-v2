package br.com.fiap.gastro_link_api_clean_architecture.core.usecase.itemcardapio;


import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.IItemCardapioGateway;

public class DeletarItemCardapioUseCase {
    private final IItemCardapioGateway itemCardapioGateway;

    private DeletarItemCardapioUseCase(IItemCardapioGateway itemCardapioGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
    }

    public static DeletarItemCardapioUseCase criar(IItemCardapioGateway itemCardapioGateway) {
        return new DeletarItemCardapioUseCase(itemCardapioGateway);
    }

    public void processar(Long idItemCardapio) {
        this.itemCardapioGateway.deletarItemCardapio(idItemCardapio);
    }
}
