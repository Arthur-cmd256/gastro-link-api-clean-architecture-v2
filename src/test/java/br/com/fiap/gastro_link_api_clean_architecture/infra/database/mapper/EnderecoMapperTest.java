package br.com.fiap.gastro_link_api_clean_architecture.infra.database.mapper;

import br.com.fiap.gastro_link_api_clean_architecture.core.domain.Endereco;
import br.com.fiap.gastro_link_api_clean_architecture.infra.database.jpa.entity.EnderecoJpaEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnderecoMapperTest {

    @Test
    void toJpaEntity_shouldMapAllFields() {
        Endereco endereco = Endereco.criar(1L, "Av. Paulista", "Apto 100", "1000", "Bela Vista", "São Paulo", "SP", "04184-000");

        EnderecoJpaEntity jpa = EnderecoMapper.toJpaEntity(endereco);

        assertNotNull(jpa);
        assertEquals(endereco.getId(), jpa.getId());
        assertEquals(endereco.getLogradouro(), jpa.getLogradouro());
        assertEquals(endereco.getNumero(), jpa.getNumero());
        assertEquals(endereco.getComplemento(), jpa.getComplemento());
        assertEquals(endereco.getBairro(), jpa.getBairro());
        assertEquals(endereco.getCidade(), jpa.getCidade());
        assertEquals(endereco.getUf(), jpa.getUf());
        assertEquals(endereco.getCep(), jpa.getCep());
    }

    @Test
    void toDomain_shouldMapAllFieldsIncludingId() {
        EnderecoJpaEntity jpa = new EnderecoJpaEntity();
        jpa.setId(2L);
        jpa.setLogradouro("Rua das Flores");
        jpa.setNumero("123");
        jpa.setComplemento("Casa");
        jpa.setBairro("Centro");
        jpa.setCidade("Rio de Janeiro");
        jpa.setUf("RJ");
        jpa.setCep("20000-000");

        Endereco domain = EnderecoMapper.toDomain(jpa);

        assertNotNull(domain);
        assertEquals(jpa.getId(), domain.getId());
        assertEquals(jpa.getLogradouro(), domain.getLogradouro());
        assertEquals(jpa.getNumero(), domain.getNumero());
        assertEquals(jpa.getComplemento(), domain.getComplemento());
        assertEquals(jpa.getBairro(), domain.getBairro());
        assertEquals(jpa.getCidade(), domain.getCidade());
        assertEquals(jpa.getUf(), domain.getUf());
        assertEquals(jpa.getCep(), domain.getCep());
    }
}
