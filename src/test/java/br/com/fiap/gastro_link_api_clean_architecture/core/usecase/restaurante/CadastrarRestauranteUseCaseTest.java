package br.com.fiap.gastro_link_api_clean_architecture.core.usecase.restaurante;


import br.com.fiap.gastro_link_api_clean_architecture.core.domain.*;
import br.com.fiap.gastro_link_api_clean_architecture.core.dto.CadastrarRestauranteInput;
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

class CadastrarRestauranteUseCaseTest {

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

    @DisplayName("Cadastrar restaurante com sucesso")
    @Test
    void testCadastrarRestauranteComSucesso() {
        String tipoCozinhaNome = "Italiana";
        TipoCozinha tipoCozinha = TipoCozinha.criar(tipoCozinhaNome);
        String nomeRestaurante = "Canto do sabor";
        String horarioFuncionamentoRestaurante = "Terça a Domingo, das 11hs as 22hs";
        CadastrarRestauranteInput cadastrarRestauranteInput = new CadastrarRestauranteInput(
                nomeRestaurante,
                this.donoRestaurante.getId(),
                this.enderecoResturante,
                tipoCozinha,
                horarioFuncionamentoRestaurante
        );
        when(this.usuarioGateway.buscarUsuarioPorId(anyLong())).thenReturn(Optional.ofNullable(this.donoRestaurante));
        when(this.restauranteGateway.salvarRestaurante(any(Restaurante.class))).thenAnswer(i -> i.getArgument(0));

        final Restaurante restauranteCriado = CadastrarRestauranteUseCase.criar(this.restauranteGateway, this.usuarioGateway).processar(cadastrarRestauranteInput);

        verify(this.usuarioGateway, times(1)).buscarUsuarioPorId(anyLong());
        verify(this.restauranteGateway, times(1)).salvarRestaurante(any(Restaurante.class));
        assertNotNull(restauranteCriado);
        assertEquals(nomeRestaurante, restauranteCriado.getNome());
    }

    @DisplayName("Cadastrar restaurante com erro (Usuario não encontrado)")
    @Test
    void testCadastrarRestauranteComErroUsuarioNaoEncontrado() {
        String tipoCozinhaNome = "Italiana";
        TipoCozinha tipoCozinha = TipoCozinha.criar(tipoCozinhaNome);
        String nomeRestaurante = "Canto do sabor";
        String horarioFuncionamentoRestaurante = "Terça a Domingo, das 11hs as 22hs";
        CadastrarRestauranteInput cadastrarRestauranteInput = new CadastrarRestauranteInput(
                nomeRestaurante,
                this.donoRestaurante.getId(),
                this.enderecoResturante,
                tipoCozinha,
                horarioFuncionamentoRestaurante
        );
        when(this.usuarioGateway.buscarUsuarioPorId(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> {
            CadastrarRestauranteUseCase
                    .criar(this.restauranteGateway, this.usuarioGateway)
                    .processar(cadastrarRestauranteInput);
        })
                .isInstanceOf(UsuarioNaoEncontradoException.class)
                .hasMessage("Usuario com ID " + this.donoRestaurante.getId() + " não foi encontrado.");

        verify(this.usuarioGateway, times(1)).buscarUsuarioPorId(anyLong());
        verify(this.restauranteGateway, never()).salvarRestaurante(any(Restaurante.class));
    }
}