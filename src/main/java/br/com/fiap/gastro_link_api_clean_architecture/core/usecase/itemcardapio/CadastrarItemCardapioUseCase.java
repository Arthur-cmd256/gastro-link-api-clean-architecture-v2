package br.com.fiap.gastro_link_api_clean_architecture.core.usecase.itemcardapio;


import br.com.fiap.gastro_link_api_clean_architecture.core.domain.ItemCardapio;
import br.com.fiap.gastro_link_api_clean_architecture.core.domain.Restaurante;
import br.com.fiap.gastro_link_api_clean_architecture.core.dto.CadastrarItemCardapioInput;
import br.com.fiap.gastro_link_api_clean_architecture.core.exception.DadoObrigatorioException;
import br.com.fiap.gastro_link_api_clean_architecture.core.exception.RestauranteNaoEncontradoException;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.IItemCardapioGateway;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.IRestauranteGateway;

public class CadastrarItemCardapioUseCase {

    IItemCardapioGateway itemCardapioGateway;
    IRestauranteGateway restauranteGateway;

    private CadastrarItemCardapioUseCase(IItemCardapioGateway itemCardapioGateway,  IRestauranteGateway restauranteGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
        this.restauranteGateway = restauranteGateway;
    }

    public static CadastrarItemCardapioUseCase criar(IItemCardapioGateway itemCardapioGateway,  IRestauranteGateway restauranteGateway) {
        return new CadastrarItemCardapioUseCase(itemCardapioGateway,  restauranteGateway);
    }

    public ItemCardapio processar(CadastrarItemCardapioInput cadastrarItemCardapioInput) {
        Long idRestaurante = cadastrarItemCardapioInput.restauranteId();
        if (idRestaurante == null) {
            throw new DadoObrigatorioException("restauranteId");
        }
        Restaurante restaurante = this.restauranteGateway
                .buscarRestaurantePorId(idRestaurante)
                .orElseThrow(() -> new RestauranteNaoEncontradoException(idRestaurante));

        ItemCardapio itemCardapio = ItemCardapio.criar(
                cadastrarItemCardapioInput.nome(),
                cadastrarItemCardapioInput.descricao(),
                cadastrarItemCardapioInput.preco(),
                cadastrarItemCardapioInput.disponibilidadeApenasRestaurante(),
                cadastrarItemCardapioInput.caminhoFoto(),
                restaurante
        );

        return this.itemCardapioGateway.salvarItemCardapio(itemCardapio);
    }
}
