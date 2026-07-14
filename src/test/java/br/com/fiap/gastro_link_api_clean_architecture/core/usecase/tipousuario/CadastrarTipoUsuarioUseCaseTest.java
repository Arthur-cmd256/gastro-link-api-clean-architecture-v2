package br.com.fiap.gastro_link_api_clean_architecture.core.usecase.tipousuario;

import br.com.fiap.gastro_link_api_clean_architecture.core.domain.TipoUsuario;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.ITipoUsuarioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CadastrarTipoUsuarioUseCaseTest {

    ITipoUsuarioGateway tipoUsuarioGateway;

    @BeforeEach
    void setUp() {
        this.tipoUsuarioGateway = mock(ITipoUsuarioGateway.class);
    }

    @DisplayName("Cadastrar tipo usuario com sucesso")
    @Test
    void testCadastrarTipoUsuarioComSucesso() {
        String nomeTipoUsuario = "Dono de Restaurante";
        when(this.tipoUsuarioGateway.salvarTipoUsuario(any(TipoUsuario.class))).thenAnswer(i -> i.getArgument(0));

        final TipoUsuario tipoUsuario = CadastrarTipoUsuarioUseCase.criar(this.tipoUsuarioGateway).processar(nomeTipoUsuario);

        verify(this.tipoUsuarioGateway, times(1)).salvarTipoUsuario(any(TipoUsuario.class));
        assertNotNull(tipoUsuario);
        assertEquals(nomeTipoUsuario, tipoUsuario.getNome());
    }
}