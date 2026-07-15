package br.com.fiap.gastro_link_api_clean_architecture.core.domain;

import br.com.fiap.gastro_link_api_clean_architecture.core.exception.DadoObrigatorioException;
import br.com.fiap.gastro_link_api_clean_architecture.core.exception.RestauranteDeveTerDonoException;
import br.com.fiap.gastro_link_api_clean_architecture.core.exception.UsuarioNaoPossuiPermissaoDeDonoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RestauranteTest {

    @Test
    void creating_valid_restaurante_shouldSucceed() {
        TipoUsuario tipoUsuario = TipoUsuario.criar(2L, "Dono");
        Usuario dono = Usuario.criar(3L, "Carlos", "carlos@example.com", tipoUsuario, Boolean.TRUE);
        Endereco endereco = Endereco.criar(5L, "Av", "Apto", "10", "Bairro", "Cidade", "UF", "00000");
        TipoCozinha tipoCozinha = TipoCozinha.criar(6L, "Italiana");

        Restaurante r = Restaurante.criar("Nome", endereco, tipoCozinha, "9-18", dono);
        assertNotNull(r);
        assertEquals("Nome", r.getNome());
        assertEquals("Carlos", r.getNomeDono());
    }

    @Test
    void creating_withNullName_shouldThrow() {
        TipoUsuario tipoUsuario = TipoUsuario.criar(2L, "Dono");
        Usuario dono = Usuario.criar(3L, "Carlos", "carlos@example.com", tipoUsuario, Boolean.TRUE);
        Endereco endereco = Endereco.criar(5L, "Av", "Apto", "10", "Bairro", "Cidade", "UF", "00000");
        TipoCozinha tipoCozinha = TipoCozinha.criar(6L, "Italiana");

        assertThrows(DadoObrigatorioException.class, () -> Restaurante.criar(null, endereco, tipoCozinha, "9-18", dono));
    }

    @Test
    void creating_without_dono_shouldThrow() {
        Endereco endereco = Endereco.criar(5L, "Av", "Apto", "10", "Bairro", "Cidade", "UF", "00000");
        TipoCozinha tipoCozinha = TipoCozinha.criar(6L, "Italiana");

        assertThrows(RestauranteDeveTerDonoException.class, () -> Restaurante.criar("Nome", endereco, tipoCozinha, "9-18", null));
    }

    @Test
    void creating_with_dono_without_permission_shouldThrow() {
        TipoUsuario tipoUsuario = TipoUsuario.criar(2L, "Cliente");
        Usuario dono = Usuario.criar(3L, "Carlos", "carlos@example.com", tipoUsuario, Boolean.FALSE);
        Endereco endereco = Endereco.criar(5L, "Av", "Apto", "10", "Bairro", "Cidade", "UF", "00000");
        TipoCozinha tipoCozinha = TipoCozinha.criar(6L, "Italiana");

        assertThrows(UsuarioNaoPossuiPermissaoDeDonoException.class, () -> Restaurante.criar("Nome", endereco, tipoCozinha, "9-18", dono));
    }
}
