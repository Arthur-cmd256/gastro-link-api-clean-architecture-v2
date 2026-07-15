package br.com.fiap.gastro_link_api_clean_architecture.infra.database.mapper;

import br.com.fiap.gastro_link_api_clean_architecture.core.domain.TipoUsuario;
import br.com.fiap.gastro_link_api_clean_architecture.infra.database.jpa.entity.TipoUsuarioJpaEntity;
import br.com.fiap.gastro_link_api_clean_architecture.core.dto.CadastrarTipoUsuarioOutDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TipoUsuarioMapperTest {

    @Test
    void toJpaEntity_and_toDomain_shouldMapAllFields() {
        TipoUsuario domain = TipoUsuario.criar(2L, "Cliente");

        var jpa = TipoUsuarioMapper.toJpaEntity(domain);
        assertNotNull(jpa);
        assertEquals(2L, jpa.getId());
        assertEquals("Cliente", jpa.getNome());

        var back = TipoUsuarioMapper.toDomain(jpa);
        assertNotNull(back);
        assertEquals(2L, back.getId());
        assertEquals("Cliente", back.getNome());
    }

    @Test
    void toCriarTipoUsuarioResponse_shouldMapOutDTO() {
        CadastrarTipoUsuarioOutDTO out = new CadastrarTipoUsuarioOutDTO(12L, "Gerente");

        var resp = TipoUsuarioMapper.toCriarTipoUsuarioResponse(out);

        assertNotNull(resp);
        assertEquals(12L, resp.id());
        assertEquals("Gerente", resp.nome());
    }

    @Test
    void mappingMethods_shouldHandleNull() {
        assertNull(TipoUsuarioMapper.toJpaEntity(null));
        assertNull(TipoUsuarioMapper.toDomain(null));
        assertNull(TipoUsuarioMapper.toCadastrarTipoUsuarioInputDTO(null));
        assertNull(TipoUsuarioMapper.toCriarTipoUsuarioResponse(null));
        assertNull(TipoUsuarioMapper.toAtualizarTipoUsuarioInputDTO(1L, null));
    }
}
