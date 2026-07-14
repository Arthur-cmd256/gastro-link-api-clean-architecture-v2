package br.com.fiap.gastro_link_api_clean_architecture.core.usecase.tipousuario;

import br.com.fiap.gastro_link_api_clean_architecture.core.domain.TipoUsuario;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.ITipoUsuarioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

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
}