package br.com.fiap.gastro_link_api_clean_architecture.infra.database.mapper;

import br.com.fiap.gastro_link_api_clean_architecture.core.domain.TipoCozinha;
import br.com.fiap.gastro_link_api_clean_architecture.infra.database.jpa.entity.TipoCozinhaJpaEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TipoCozinhaMapperTest {

    @Test
    void toJpaEntity_shouldMapAllFields() {
        TipoCozinha tipo = TipoCozinha.criar(4L, "Mexicana");

        var jpa = TipoCozinhaMapper.toJpaEntity(tipo);

        assertNotNull(jpa);
        assertEquals(4L, jpa.getId());
        assertEquals("Mexicana", jpa.getNome());
    }

    @Test
    void toDomain_shouldMapAllFields() {
        TipoCozinhaJpaEntity jpa = new TipoCozinhaJpaEntity();
        jpa.setId(8L);
        jpa.setNome("Japonesa");

        var domain = TipoCozinhaMapper.toDomain(jpa);

        assertNotNull(domain);
        assertEquals(8L, domain.getId());
        assertEquals("Japonesa", domain.getNome());
    }

    @Test
    void mappingMethods_shouldHandleNull() {
        assertNull(TipoCozinhaMapper.toJpaEntity(null));
        assertNull(TipoCozinhaMapper.toDomain(null));
    }
}
