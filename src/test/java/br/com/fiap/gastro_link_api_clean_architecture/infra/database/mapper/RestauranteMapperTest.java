package br.com.fiap.gastro_link_api_clean_architecture.infra.database.mapper;

import br.com.fiap.gastro_link_api_clean_architecture.core.domain.Endereco;
import br.com.fiap.gastro_link_api_clean_architecture.core.domain.Restaurante;
import br.com.fiap.gastro_link_api_clean_architecture.core.domain.TipoCozinha;
import br.com.fiap.gastro_link_api_clean_architecture.core.domain.TipoUsuario;
import br.com.fiap.gastro_link_api_clean_architecture.core.domain.Usuario;
import br.com.fiap.gastro_link_api_clean_architecture.core.dto.AtualizarRestauranteInput;
import br.com.fiap.gastro_link_api_clean_architecture.core.dto.CadastrarRestauranteInput;
import br.com.fiap.gastro_link_api_clean_architecture.infra.database.jpa.entity.EnderecoJpaEntity;
import br.com.fiap.gastro_link_api_clean_architecture.infra.database.jpa.entity.RestauranteJpaEntity;
import br.com.fiap.gastro_link_api_clean_architecture.infra.database.jpa.entity.TipoCozinhaJpaEntity;
import br.com.fiap.gastro_link_api_clean_architecture.infra.database.jpa.entity.UsuarioJpaEntity;
import br.com.fiap.gastro_link_api_clean_architecture.infra.web.request.EnderecoRequest;
import br.com.fiap.gastro_link_api_clean_architecture.infra.web.request.RestauranteRequest;
import br.com.fiap.gastro_link_api_clean_architecture.infra.web.response.RestauranteResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RestauranteMapperTest {

    @Test
    void toCriarRestauranteResponse_shouldMapDomainToResponse() {
        TipoUsuario tipoUsuario = TipoUsuario.criar(2L, "Dono");
        Usuario dono = Usuario.criar(3L, "Carlos", "carlos@example.com", tipoUsuario, Boolean.TRUE);
        Endereco endereco = Endereco.criar(5L, "Av das Flores", "Apto 1", "100", "Centro", "CidadeX", "UF", "12345000");
        TipoCozinha tipoCozinha = TipoCozinha.criar(6L, "Italiana");
        Restaurante restaurante = Restaurante.criar(10L, "Restaurante X", endereco, tipoCozinha, "9-18", dono);

        RestauranteResponse resp = RestauranteMapper.toCriarRestauranteResponse(restaurante);

        assertNotNull(resp);
        assertEquals(10L, resp.id());
        assertEquals("Restaurante X", resp.nome());
        assertEquals(3L, resp.donoId());
        assertNotNull(resp.endereco());
        assertEquals(5L, resp.endereco().id());
        assertEquals("Av das Flores", resp.endereco().logradouro());
        assertEquals("Italiana", resp.tipoCozinha());
        assertEquals("9-18", resp.horarioFuncionamento());
    }

    @Test
    void toJpaEntity_shouldMapAllFields() {
        TipoUsuario tipoUsuario = TipoUsuario.criar(2L, "Dono");
        Usuario dono = Usuario.criar(3L, "Carlos", "carlos@example.com", tipoUsuario, Boolean.TRUE);
        Endereco endereco = Endereco.criar(5L, "Rua A", "Bloco B", "10", "Bairro", "CidadeY", "UF", "98765000");
        TipoCozinha tipoCozinha = TipoCozinha.criar(6L, "Brasileira");
        Restaurante restaurante = Restaurante.criar(20L, "Bistrô", endereco, tipoCozinha, "10-22", dono);

        var jpa = RestauranteMapper.toJpaEntity(restaurante);

        assertNotNull(jpa);
        assertEquals(20L, jpa.getId());
        assertEquals("Bistrô", jpa.getNome());
        assertEquals("10-22", jpa.getHorarioFuncionamento());
        assertNotNull(jpa.getEnderecoCompleto());
        assertEquals(5L, jpa.getEnderecoCompleto().getId());
        assertEquals("Rua A", jpa.getEnderecoCompleto().getLogradouro());
        assertNotNull(jpa.getDono());
        assertEquals(3L, jpa.getDono().getId());
        assertEquals("carlos@example.com", jpa.getDono().getEnderecoDeEmail());
        assertNotNull(jpa.getTipoCozinha());
        assertEquals(6L, jpa.getTipoCozinha().getId());
    }

    @Test
    void toDomain_shouldMapAllFields() {
        RestauranteJpaEntity jpa = new RestauranteJpaEntity();
        jpa.setId(33L);
        jpa.setNome("Casa");
        jpa.setHorarioFuncionamento("8-20");

        EnderecoJpaEntity e = new EnderecoJpaEntity();
        e.setId(44L);
        e.setLogradouro("Rua B");
        e.setNumero("200");
        e.setComplemento("Casa");
        e.setBairro("Centro");
        e.setCidade("CidadeZ");
        e.setUf("UF");
        e.setCep("11111000");
        jpa.setEnderecoCompleto(e);

        UsuarioJpaEntity u = new UsuarioJpaEntity();
        u.setId(55L);
        u.setNome("Ana");
        u.setEnderecoDeEmail("ana@example.com");
        u.setPossuiPermissaoDeDono(Boolean.TRUE);
        jpa.setDono(u);

        TipoCozinhaJpaEntity tc = new TipoCozinhaJpaEntity();
        tc.setId(66L);
        tc.setNome("Francesa");
        jpa.setTipoCozinha(tc);

        var domain = RestauranteMapper.toDomain(jpa);

        assertNotNull(domain);
        assertEquals(33L, domain.getId());
        assertEquals("Casa", domain.getNome());
        assertNotNull(domain.getEndereco());
        assertEquals(44L, domain.getEndereco().getId());
        assertEquals("Rua B", domain.getEndereco().getLogradouro());
        assertNotNull(domain.getDono());
        assertEquals(55L, domain.getDono().getId());
        assertEquals("ana@example.com", domain.getDono().getEnderecoDeEmail());
        assertNotNull(domain.getTipoCozinha());
        assertEquals(66L, domain.getTipoCozinha().getId());
    }

    @Test
    void toCadastrar_and_toAtualizar_shouldMapFromRequest() {
        EnderecoRequest er = new EnderecoRequest("Rua C", "Apto 2", "77", "Vila", "CidadeW", "UF", "22222000");
        RestauranteRequest req = new RestauranteRequest("Cantina", 5L, er, "Italiana", "11-23");

        CadastrarRestauranteInput cadastrar = RestauranteMapper.toCadastrarRestauranteInput(req);
        assertNotNull(cadastrar);
        assertEquals("Cantina", cadastrar.nome());
        assertEquals(5L, cadastrar.donoId());
        assertNotNull(cadastrar.endereco());
        assertEquals("Rua C", cadastrar.endereco().getLogradouro());
        assertEquals("Italiana", cadastrar.tipoCozinha().getNome());

        AtualizarRestauranteInput atualizar = RestauranteMapper.toAtualizarRestauranteInput(88L, req);
        assertNotNull(atualizar);
        assertEquals(88L, atualizar.id());
        assertEquals("Cantina", atualizar.nome());
        assertEquals(5L, atualizar.donoId());
    }

    @Test
    void toCriarRestauranteResponse_withNullEnderecoId_shouldMapNullId() {
        TipoUsuario tipoUsuario = TipoUsuario.criar(2L, "Dono");
        Usuario dono = Usuario.criar(3L, "Carlos", "carlos@example.com", tipoUsuario, Boolean.TRUE);
        Endereco endereco = Endereco.criar(null, "Av sem id", null, "100", "Centro", "CidadeX", "UF", "00000000");
        TipoCozinha tipoCozinha = TipoCozinha.criar(6L, "Italiana");
        Restaurante restaurante = Restaurante.criar(10L, "Restaurante Y", endereco, tipoCozinha, "9-18", dono);

        RestauranteResponse resp = RestauranteMapper.toCriarRestauranteResponse(restaurante);
        assertNotNull(resp);
        assertNull(resp.endereco().id());
        assertEquals("Av sem id", resp.endereco().logradouro());
    }

    @Test
    void toJpaEntity_withNullOptionalComplemento_shouldMapNullComplemento() {
        TipoUsuario tipoUsuario = TipoUsuario.criar(2L, "Dono");
        Usuario dono = Usuario.criar(3L, "Carlos", "carlos@example.com", tipoUsuario, Boolean.TRUE);
        Endereco endereco = Endereco.criar(5L, "Rua Sem Complemento", null, "20", "Bairro", "CidadeY", "UF", "33333000");
        TipoCozinha tipoCozinha = TipoCozinha.criar(6L, "Brasileira");
        Restaurante restaurante = Restaurante.criar(20L, "Bistrô", endereco, tipoCozinha, "10-22", dono);

        var jpa = RestauranteMapper.toJpaEntity(restaurante);
        assertNotNull(jpa);
        assertNotNull(jpa.getEnderecoCompleto());
        assertNull(jpa.getEnderecoCompleto().getComplemento());
    }

    @Test
    void mappingMethods_shouldHandleNull() {
        assertNull(RestauranteMapper.toCriarRestauranteResponse(null));
        assertNull(RestauranteMapper.toDomain(null));
        assertNull(RestauranteMapper.toJpaEntity(null));
        assertNull(RestauranteMapper.toCadastrarRestauranteInput(null));
        assertNull(RestauranteMapper.toAtualizarRestauranteInput(1L, null));
    }
}

