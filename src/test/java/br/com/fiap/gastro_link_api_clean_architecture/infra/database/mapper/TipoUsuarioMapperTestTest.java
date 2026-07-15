package br.com.fiap.gastro_link_api_clean_architecture.infra.database.mapper;

import br.com.fiap.gastro_link_api_clean_architecture.infra.web.request.TipoUsuarioRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TipoUsuarioMapperTestTest {

    @Test
    void toCadastrar_and_toAtualizar_shouldMapFromRequest() {
        TipoUsuarioRequest req = new TipoUsuarioRequest("Administrador");

        var cadastrar = TipoUsuarioMapper.toCadastrarTipoUsuarioInputDTO(req);
        assertNotNull(cadastrar);
        assertEquals("Administrador", cadastrar.nome());

        var atualizar = TipoUsuarioMapper.toAtualizarTipoUsuarioInputDTO(77L, req);
        assertNotNull(atualizar);
        assertEquals(77L, atualizar.id());
        assertEquals("Administrador", atualizar.nome());
    }

    @Test
    void mappingMethods_shouldHandleNull() {
        assertNull(TipoUsuarioMapper.toCadastrarTipoUsuarioInputDTO(null));
        assertNull(TipoUsuarioMapper.toAtualizarTipoUsuarioInputDTO(1L, null));
    }
}
