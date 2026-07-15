package br.com.fiap.gastro_link_api_clean_architecture.core.usecase.tipousuario;

import br.com.fiap.gastro_link_api_clean_architecture.core.domain.TipoUsuario;
import br.com.fiap.gastro_link_api_clean_architecture.core.exception.RestauranteNaoEncontradoException;
import br.com.fiap.gastro_link_api_clean_architecture.core.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.ITipoUsuarioGateway;
import br.com.fiap.gastro_link_api_clean_architecture.core.usecase.restaurante.BuscarRestauranteUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BuscarTipoUsuarioUseCaseTest {
    ITipoUsuarioGateway tipoUsuarioGateway;

    @BeforeEach
    void setUp() {
        this.tipoUsuarioGateway = mock(ITipoUsuarioGateway.class);
    }

    @DisplayName("Buscar tipo usuario com sucesso)")
    @Test
    void testBuscarTipoUsuarioComSucesso() {
        Long id = 1L;
        String nomeTipoUsuario = "Dono de Restaurante";
        TipoUsuario tipoUsuario = TipoUsuario.criar(id, nomeTipoUsuario);
        when(this.tipoUsuarioGateway.buscarTipoUsuarioPorId(any(Long.class))).thenReturn(Optional.of(tipoUsuario));

        TipoUsuario tipoUsuarioRetorno = BuscarTipoUsuarioUseCase.criar(this.tipoUsuarioGateway).processar(id);

        verify(this.tipoUsuarioGateway, times(1)).buscarTipoUsuarioPorId(any(Long.class));
        assertNotNull(tipoUsuarioRetorno);
        assertEquals(nomeTipoUsuario, tipoUsuarioRetorno.getNome());
    }

    @DisplayName("Buscar tipo usuario com erro (Tipo usuario Nao Encontrado)")
    @Test
    void testBuscarRestauranteComErroRestauranteNaoEncontrado() {
        Long idTipoUsuario = 1L;
        when(this.tipoUsuarioGateway.buscarTipoUsuarioPorId(any(Long.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> {
            BuscarTipoUsuarioUseCase.criar(this.tipoUsuarioGateway).processar(idTipoUsuario);
        })
                .isInstanceOf(TipoUsuarioNaoEncontradoException.class)
                .hasMessage("TipoUsuario com ID "+ idTipoUsuario +" não foi encontrado.");
    }
}