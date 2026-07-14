package br.com.fiap.gastro_link_api_clean_architecture.core.usecase.itemcardapio;

import br.com.fiap.gastro_link_api_clean_architecture.core.domain.ItemCardapio;
import br.com.fiap.gastro_link_api_clean_architecture.core.domain.Restaurante;
import br.com.fiap.gastro_link_api_clean_architecture.core.dto.CadastrarItemCardapioInput;
import br.com.fiap.gastro_link_api_clean_architecture.core.exception.DadoObrigatorioException;
import br.com.fiap.gastro_link_api_clean_architecture.core.exception.RestauranteNaoEncontradoException;
import br.com.fiap.gastro_link_api_clean_architecture.core.factory.RestauranteFactory;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.IItemCardapioGateway;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.IRestauranteGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class CadastrarItemCardapioUseCaseTest {

    IItemCardapioGateway itemCardapioGateway;
    IRestauranteGateway restauranteGateway;

    @BeforeEach
    void setUp() {
        this.itemCardapioGateway = Mockito.mock(IItemCardapioGateway.class);
        this.restauranteGateway = Mockito.mock(IRestauranteGateway.class);
    }

    @DisplayName("Cadastrar item cardapio com sucesso")
    @Test
    void CadastrarItemCardapioComSucesso() {
        String nomeItemCardapio = "nomeItemCardapio";
        String descricaoItemCardapio = "descricaoItemCardapio";
        BigDecimal precoItemCardapio = BigDecimal.valueOf(35.99);
        Boolean disponibilidadeApenasRestauranteItemCardapio = Boolean.FALSE;
        String caminhoFotoItemCardapio = "fotoItemCardapio";

        Restaurante restauranteCadastrado = RestauranteFactory.criarRestaurantePadrao();
        Long idRestaurante = restauranteCadastrado.getId();
        when(this.restauranteGateway.buscarRestaurantePorId(anyLong())).thenReturn(Optional.of(restauranteCadastrado));
        when(this.itemCardapioGateway.salvarItemCardapio(any(ItemCardapio.class))).thenAnswer(i -> i.getArgument(0));
        CadastrarItemCardapioInput cadastrarItemCardapioInput = new CadastrarItemCardapioInput(
                nomeItemCardapio,
                descricaoItemCardapio,
                precoItemCardapio,
                disponibilidadeApenasRestauranteItemCardapio,
                caminhoFotoItemCardapio,
                idRestaurante
        );

        ItemCardapio itemCardapioCadastrado = CadastrarItemCardapioUseCase
                .criar(this.itemCardapioGateway, this.restauranteGateway)
                .processar(cadastrarItemCardapioInput);




        assertNotNull(itemCardapioCadastrado);
        assertEquals(nomeItemCardapio, itemCardapioCadastrado.getNome());
        assertEquals(descricaoItemCardapio, itemCardapioCadastrado.getDescricao());
        assertEquals(precoItemCardapio, itemCardapioCadastrado.getPreco());
        assertEquals(disponibilidadeApenasRestauranteItemCardapio, itemCardapioCadastrado.getDisponibilidadeApenasRestaurante());
        assertEquals(caminhoFotoItemCardapio, itemCardapioCadastrado.getCaminhoFoto());
        assertEquals(restauranteCadastrado.getNome(), itemCardapioCadastrado.getNomeRestaurante());
    }

    @DisplayName("Cadastrar item cardapio com erro (idRestaurante vazio)")
    @Test
    void CadastrarItemCardapioComErroIdRestauranteVazio() {
        String nomeItemCardapio = "nomeItemCardapio";
        String descricaoItemCardapio = "descricaoItemCardapio";
        BigDecimal precoItemCardapio = BigDecimal.valueOf(35.99);
        Boolean disponibilidadeApenasRestauranteItemCardapio = Boolean.FALSE;
        String caminhoFotoItemCardapio = "fotoItemCardapio";
        CadastrarItemCardapioInput cadastrarItemCardapioInput = new CadastrarItemCardapioInput(
                nomeItemCardapio,
                descricaoItemCardapio,
                precoItemCardapio,
                disponibilidadeApenasRestauranteItemCardapio,
                caminhoFotoItemCardapio,
                null
        );

        assertThatThrownBy(() -> {
            CadastrarItemCardapioUseCase
                    .criar(this.itemCardapioGateway, this.restauranteGateway)
                    .processar(cadastrarItemCardapioInput);
        })
                .isInstanceOf(DadoObrigatorioException.class)
                .hasMessage("O campo 'restauranteId' é obrigatório.");

        verify(this.restauranteGateway, never()).buscarRestaurantePorId(anyLong());
        verify(this.itemCardapioGateway, never()).salvarItemCardapio(any());
    }

    @DisplayName("Cadastrar item cardapio com erro (Restaurante não encotrado)")
    @Test
    void CadastrarItemCardapioComErroRestauranteNaoEncotrado() {
        when(this.restauranteGateway.buscarRestaurantePorId(anyLong())).thenReturn(Optional.empty());
        String nomeItemCardapio = "nomeItemCardapio";
        String descricaoItemCardapio = "descricaoItemCardapio";
        BigDecimal precoItemCardapio = BigDecimal.valueOf(35.99);
        Boolean disponibilidadeApenasRestauranteItemCardapio = Boolean.FALSE;
        String caminhoFotoItemCardapio = "fotoItemCardapio";
        CadastrarItemCardapioInput cadastrarItemCardapioInput = new CadastrarItemCardapioInput(
                nomeItemCardapio,
                descricaoItemCardapio,
                precoItemCardapio,
                disponibilidadeApenasRestauranteItemCardapio,
                caminhoFotoItemCardapio,
                1L
        );

        assertThatThrownBy(() -> {
            CadastrarItemCardapioUseCase
                    .criar(this.itemCardapioGateway, this.restauranteGateway)
                    .processar(cadastrarItemCardapioInput);
        })
                .isInstanceOf(RestauranteNaoEncontradoException.class)
                .hasMessage("Restaurante com ID " + cadastrarItemCardapioInput.restauranteId() + " não foi encontrado.");

        verify(this.restauranteGateway, times(1)).buscarRestaurantePorId(anyLong());
        verify(this.itemCardapioGateway, never()).salvarItemCardapio(any());
    }
}