package br.com.fiap.gastro_link_api_clean_architecture.core.usecase.tipousuario;

import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.ITipoUsuarioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DeletarTipoUsuarioUseCaseTest {
    ITipoUsuarioGateway tipoUsuarioGateway;

    @BeforeEach
    void setUp() {
        this.tipoUsuarioGateway = mock(ITipoUsuarioGateway.class);
    }

    @Test
    void testApagarTipoUsuarioComSucesso() {
        Long id = 1L;
        when(this.tipoUsuarioGateway.deletarTipoUsuario(any(Long.class))).thenReturn(null);

        DeletarTipoUsuarioUseCase.criar(this.tipoUsuarioGateway).processar(id);

        verify(this.tipoUsuarioGateway, times(1)).deletarTipoUsuario(any(Long.class));
    }
}