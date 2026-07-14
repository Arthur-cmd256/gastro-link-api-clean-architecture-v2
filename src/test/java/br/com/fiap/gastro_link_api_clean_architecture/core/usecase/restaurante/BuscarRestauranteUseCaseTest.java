package br.com.fiap.gastro_link_api_clean_architecture.core.usecase.restaurante;

import br.com.fiap.gastro_link_api_clean_architecture.core.domain.*;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.IRestauranteGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BuscarRestauranteUseCaseTest {
    IRestauranteGateway restauranteGateway;
    Endereco enderecoResturante;
    TipoUsuario tipoUsuario;
    Usuario donoRestaurante;

    @BeforeEach
    void setUp() {
        this.restauranteGateway = mock(IRestauranteGateway.class);
        this.enderecoResturante = enderecoSetUp(
                "Avenida Paulista",
                "1000",
                "Torre A",
                "Boa vista",
                "São Paulo",
                "SP",
                "04184-000"
        );
        this.tipoUsuario = tipoUsuarioSetUp(1L, "Dono");
        this.donoRestaurante = usuarioSetUp(1L, "João da Silva", "joao@example.com", this.tipoUsuario);
    }

    Endereco enderecoSetUp(String logradouro, String numero, String complemento, String bairro, String cidade, String uf, String cep) {
        return Endereco.criar(
                logradouro,
                numero,
                complemento,
                bairro,
                cidade,
                uf,
                cep
        );
    }

    Usuario usuarioSetUp(Long idUsuario, String nomeUsuario, String enderecoEmailUsuario, TipoUsuario tipoUsuario) {
        return Usuario.criar(
                idUsuario,
                nomeUsuario,
                enderecoEmailUsuario,
                tipoUsuario
        );
    }

    TipoUsuario tipoUsuarioSetUp(Long idTipoUsuario, String nomeTipoUsuario) {
        return TipoUsuario.criar(idTipoUsuario, nomeTipoUsuario);
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

}