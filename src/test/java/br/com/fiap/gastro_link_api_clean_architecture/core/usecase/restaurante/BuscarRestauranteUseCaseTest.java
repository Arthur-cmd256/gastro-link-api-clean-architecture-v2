package br.com.fiap.gastro_link_api_clean_architecture.core.usecase.restaurante;

import br.com.fiap.gastro_link_api_clean_architecture.core.domain.*;
import br.com.fiap.gastro_link_api_clean_architecture.core.exception.RestauranteNaoEncontradoException;
import br.com.fiap.gastro_link_api_clean_architecture.core.factory.EnderecoFactory;
import br.com.fiap.gastro_link_api_clean_architecture.core.factory.UsuarioFactory;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.IRestauranteGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BuscarRestauranteUseCaseTest {
    IRestauranteGateway restauranteGateway;
    Endereco enderecoResturante;
    Usuario donoRestaurante;

    @BeforeEach
    void setUp() {
        this.restauranteGateway = mock(IRestauranteGateway.class);
        this.enderecoResturante = EnderecoFactory.criarEnderecoPadrao();
        this.donoRestaurante = UsuarioFactory.criarDonoPadrao();
    }

    @DisplayName("Buscar restaurante com sucesso")
    @Test
    void testBuscarRestauranteComSucesso() {
        String tipoCozinhaNome = "Italiana";
        TipoCozinha tipoCozinha = TipoCozinha.criar(tipoCozinhaNome);
        Long idRestaurante = 1L;
        String nomeRestaurante = "Canto do sabor";
        String horarioFuncionamentoRestaurante = "Terça a Domingo, das 11hs as 22hs";
        Restaurante restauranteCadastrado = Restaurante.criar(
                idRestaurante,
                nomeRestaurante,
                this.enderecoResturante,
                tipoCozinha,
                horarioFuncionamentoRestaurante,
                this.donoRestaurante
        );
        when(this.restauranteGateway.buscarRestaurantePorId(any(Long.class))).thenReturn(Optional.of(restauranteCadastrado));

        Restaurante restauranteBuscado = BuscarRestauranteUseCase.criar(this.restauranteGateway).processar(idRestaurante);

        verify(this.restauranteGateway, times(1)).buscarRestaurantePorId(any(Long.class));
        assertNotNull(restauranteBuscado);
        assertEquals(nomeRestaurante, restauranteBuscado.getNome());
    }

    @DisplayName("Buscar restaurante com erro (Restaurante Nao Encontrado)")
    @Test
    void testBuscarRestauranteComErroRestauranteNaoEncontrado() {
        Long idRestaurante = 1L;
        when(this.restauranteGateway.buscarRestaurantePorId(any(Long.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> {
            BuscarRestauranteUseCase.criar(this.restauranteGateway).processar(idRestaurante);
        })
                .isInstanceOf(RestauranteNaoEncontradoException.class)
                .hasMessage("Restaurante com ID "+ idRestaurante +" não foi encontrado.");
    }

}