package br.com.fiap.gastro_link_api_clean_architecture.core.usecase.restaurante;

import br.com.fiap.gastro_link_api_clean_architecture.core.domain.*;
import br.com.fiap.gastro_link_api_clean_architecture.core.dto.AtualizarRestauranteInput;
import br.com.fiap.gastro_link_api_clean_architecture.core.exception.RestauranteNaoEncontradoException;
import br.com.fiap.gastro_link_api_clean_architecture.core.exception.UsuarioNaoEncontradoException;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.IRestauranteGateway;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.IUsuarioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AtualizarRestauranteUseCaseTest {
    IRestauranteGateway restauranteGateway;
    IUsuarioGateway usuarioGateway;
    Endereco enderecoResturante;
    TipoUsuario tipoUsuario;
    Usuario donoRestaurante;

    @BeforeEach
    void setUp() {
        this.restauranteGateway = mock(IRestauranteGateway.class);
        this.usuarioGateway = mock(IUsuarioGateway.class);
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

    @DisplayName("Atualizar restaurante com sucesso")
    @Test
    void testAtualizarRestauranteComSucesso() {
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
        String tipoCozinhaNomeAtualizacao = "Arabe";
        TipoCozinha tipoCozinhaAtualizacao = TipoCozinha.criar(tipoCozinhaNomeAtualizacao);
        String nomeRestauranteAtualizacao = "Esfiha Imigrantes";
        String horarioFuncionamentoRestauranteAtualizacao = "Terça a Domingo, das 18hs as 02hs";
        Endereco enderecoResturanteAtualizacao = enderecoSetUp(
                "Avenida Paulista",
                "1001",
                "Torre B",
                "Boa vista",
                "São Paulo",
                "SP",
                "01310-100"
        );
        AtualizarRestauranteInput atualizarRestauranteInput = new AtualizarRestauranteInput(
                idRestaurante,
                nomeRestauranteAtualizacao,
                enderecoResturanteAtualizacao,
                tipoCozinhaAtualizacao,
                horarioFuncionamentoRestauranteAtualizacao,
                this.donoRestaurante.getId()
        );
        when(this.restauranteGateway.salvarRestaurante(any(Restaurante.class))).thenAnswer(i -> i.getArgument(0));

        Restaurante restauranteAtualizado = AtualizarRestauranteUseCase.criar(this.restauranteGateway, this.usuarioGateway).processar(atualizarRestauranteInput);

        verify(this.restauranteGateway, times(1)).buscarRestaurantePorId(any(Long.class));
        verify(this.usuarioGateway, never()).buscarUsuarioPorId(any(Long.class));
        verify(this.restauranteGateway, times(1)).salvarRestaurante(any(Restaurante.class));
        assertNotNull(restauranteAtualizado);
        assertEquals(nomeRestauranteAtualizacao, restauranteAtualizado.getNome());
        assertEquals(tipoCozinhaAtualizacao.getNome(), restauranteAtualizado.getNomeTipoCozinha());
        assertEquals(this.donoRestaurante.getNome(), restauranteAtualizado.getNomeDono());
        assertEquals(enderecoResturanteAtualizacao.getCep(), restauranteAtualizado.getEndereco().getCep());
    }

    @DisplayName("Atualizar restaurante com sucesso com novo dono")
    @Test
    void testAtualizarRestauranteComSucessoComNovoDono() {
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
        Long idUsuarioAtualizacao = 2L;
        Usuario donoRestauranteAtualizacao = usuarioSetUp(
                idUsuarioAtualizacao,
                "Arthur",
                "arthur@example.com",
                this.tipoUsuario
        );
        when(this.usuarioGateway.buscarUsuarioPorId(any(Long.class))).thenReturn(Optional.ofNullable(donoRestauranteAtualizacao));
        String tipoCozinhaNomeAtualizacao = "Arabe";
        TipoCozinha tipoCozinhaAtualizacao = TipoCozinha.criar(tipoCozinhaNomeAtualizacao);
        String nomeRestauranteAtualizacao = "Esfiha Imigrantes";
        String horarioFuncionamentoRestauranteAtualizacao = "Terça a Domingo, das 18hs as 02hs";
        AtualizarRestauranteInput atualizarRestauranteInput = new AtualizarRestauranteInput(
                idRestaurante,
                nomeRestauranteAtualizacao,
                this.enderecoResturante,
                tipoCozinhaAtualizacao,
                horarioFuncionamentoRestauranteAtualizacao,
                idUsuarioAtualizacao
        );
        when(this.restauranteGateway.salvarRestaurante(any(Restaurante.class))).thenAnswer(i -> i.getArgument(0));

        Restaurante restauranteAtualizado = AtualizarRestauranteUseCase.criar(this.restauranteGateway, this.usuarioGateway).processar(atualizarRestauranteInput);

        verify(this.restauranteGateway, times(1)).buscarRestaurantePorId(any(Long.class));
        verify(this.usuarioGateway, times(1)).buscarUsuarioPorId(any(Long.class));
        verify(this.restauranteGateway, times(1)).salvarRestaurante(any(Restaurante.class));
        assertNotNull(restauranteAtualizado);
        assertEquals(nomeRestauranteAtualizacao, restauranteAtualizado.getNome());
        assertEquals(tipoCozinhaAtualizacao.getNome(), restauranteAtualizado.getNomeTipoCozinha());
        assertEquals(donoRestauranteAtualizacao.getNome(), restauranteAtualizado.getNomeDono());
    }

    @DisplayName("Atualizar restaurante com erro (Restaurante não encontrado)")
    @Test
    void testAtualizarRestauranteComErroRestauranteNaoEncontrado() {
        Long idRestaurante = 1L;
        when(this.restauranteGateway.buscarRestaurantePorId(any(Long.class))).thenReturn(Optional.empty());
        String tipoCozinhaNomeAtualizacao = "Arabe";
        TipoCozinha tipoCozinhaAtualizacao = TipoCozinha.criar(tipoCozinhaNomeAtualizacao);
        String nomeRestauranteAtualizacao = "Esfiha Imigrantes";
        String horarioFuncionamentoRestauranteAtualizacao = "Terça a Domingo, das 18hs as 02hs";
        AtualizarRestauranteInput atualizarRestauranteInput = new AtualizarRestauranteInput(
                idRestaurante,
                nomeRestauranteAtualizacao,
                this.enderecoResturante,
                tipoCozinhaAtualizacao,
                horarioFuncionamentoRestauranteAtualizacao,
                this.donoRestaurante.getId()
        );

        assertThatThrownBy(() -> {
            AtualizarRestauranteUseCase.criar(this.restauranteGateway, this.usuarioGateway).processar(atualizarRestauranteInput);
        })
                .isInstanceOf(RestauranteNaoEncontradoException.class)
                .hasMessage("Restaurante com ID "+ idRestaurante +" não foi encontrado.");

        verify(this.restauranteGateway, times(1)).buscarRestaurantePorId(any(Long.class));
        verify(this.usuarioGateway, never()).buscarUsuarioPorId(any(Long.class));
        verify(this.restauranteGateway, never()).salvarRestaurante(any(Restaurante.class));
    }

    @Test
    void testAtualizarRestauranteComErroUsuarioNaoEncontrado() {
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
        Long idUsuarioAtualizacao = 20L;
        when(this.usuarioGateway.buscarUsuarioPorId(any(Long.class))).thenReturn(Optional.empty());
        String tipoCozinhaNomeAtualizacao = "Arabe";
        TipoCozinha tipoCozinhaAtualizacao = TipoCozinha.criar(tipoCozinhaNomeAtualizacao);
        String nomeRestauranteAtualizacao = "Esfiha Imigrantes";
        String horarioFuncionamentoRestauranteAtualizacao = "Terça a Domingo, das 18hs as 02hs";
        AtualizarRestauranteInput atualizarRestauranteInput = new AtualizarRestauranteInput(
                idRestaurante,
                nomeRestauranteAtualizacao,
                this.enderecoResturante,
                tipoCozinhaAtualizacao,
                horarioFuncionamentoRestauranteAtualizacao,
                idUsuarioAtualizacao
        );


        assertThatThrownBy(() -> {
            AtualizarRestauranteUseCase.criar(this.restauranteGateway, this.usuarioGateway).processar(atualizarRestauranteInput);
        })
                .isInstanceOf(UsuarioNaoEncontradoException.class)
                .hasMessage("Usuario com ID "+ idUsuarioAtualizacao +" não foi encontrado.");

        verify(this.restauranteGateway, times(1)).buscarRestaurantePorId(any(Long.class));
        verify(this.usuarioGateway, times(1)).buscarUsuarioPorId(any(Long.class));
        verify(this.restauranteGateway, never()).salvarRestaurante(any(Restaurante.class));
    }
}