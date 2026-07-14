package br.com.fiap.gastro_link_api_clean_architecture.core.usecase.tipousuario;

import br.com.fiap.gastro_link_api_clean_architecture.core.domain.TipoUsuario;
import br.com.fiap.gastro_link_api_clean_architecture.core.dto.AtualizarTipoUsuarioInputDTO;
import br.com.fiap.gastro_link_api_clean_architecture.core.exception.TipoUsuarioNaoEncontradoException;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.ITipoUsuarioGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AtualizarTipoUsuarioUseCaseTest {
    ITipoUsuarioGateway tipoUsuarioGateway;

    @BeforeEach
    void setUp() {
        this.tipoUsuarioGateway = mock(ITipoUsuarioGateway.class);
    }

    @Test
    void testAtualizarTipoUsuarioComSucesso() {
        Long idTipoUsuario = 1L;
        TipoUsuario tipoUsuario = TipoUsuario.criar(idTipoUsuario, "Dono");
        when(this.tipoUsuarioGateway.buscarTipoUsuarioPorId(any(Long.class))).thenReturn(Optional.of(tipoUsuario));
        when(this.tipoUsuarioGateway.salvarTipoUsuario(any(TipoUsuario.class))).thenAnswer(i -> i.getArgument(0));
        AtualizarTipoUsuarioInputDTO tipoUsuarioAtualizacao = new AtualizarTipoUsuarioInputDTO(idTipoUsuario, "Cliente");

        TipoUsuario tipoUsuarioAtualizado = AtualizarTipoUsuarioUseCase.criar(this.tipoUsuarioGateway).processar(tipoUsuarioAtualizacao);

        Assertions.assertEquals(tipoUsuarioAtualizacao.nome(), tipoUsuarioAtualizado.getNome());

        verify(this.tipoUsuarioGateway, times(1)).buscarTipoUsuarioPorId(any(Long.class));
    }

    @Test
    void testAtualizarTipoUsuarioComErroEntidadeNaoEncontrada() {
        when(this.tipoUsuarioGateway.buscarTipoUsuarioPorId(any(Long.class))).thenReturn(Optional.empty());
        Long idTipoUsuario = 1L;
        AtualizarTipoUsuarioInputDTO tipoUsuarioAtualizacao = new AtualizarTipoUsuarioInputDTO(idTipoUsuario, "Cliente");

        assertThatThrownBy(() -> AtualizarTipoUsuarioUseCase.criar(this.tipoUsuarioGateway).processar(tipoUsuarioAtualizacao))
                .isInstanceOf(TipoUsuarioNaoEncontradoException.class)
                .hasMessage("TipoUsuario com ID "+ idTipoUsuario +" não foi encontrado.");

        verify(this.tipoUsuarioGateway, never()).salvarTipoUsuario(any(TipoUsuario.class));
    }
}