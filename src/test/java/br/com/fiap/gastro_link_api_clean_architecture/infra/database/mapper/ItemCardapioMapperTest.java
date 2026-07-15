package br.com.fiap.gastro_link_api_clean_architecture.infra.database.mapper;

import br.com.fiap.gastro_link_api_clean_architecture.core.domain.Endereco;
import br.com.fiap.gastro_link_api_clean_architecture.core.domain.ItemCardapio;
import br.com.fiap.gastro_link_api_clean_architecture.core.domain.Restaurante;
import br.com.fiap.gastro_link_api_clean_architecture.core.domain.TipoCozinha;
import br.com.fiap.gastro_link_api_clean_architecture.core.domain.TipoUsuario;
import br.com.fiap.gastro_link_api_clean_architecture.core.domain.Usuario;
import br.com.fiap.gastro_link_api_clean_architecture.core.dto.AtualizarItemCardapioInput;
import br.com.fiap.gastro_link_api_clean_architecture.core.dto.CadastrarItemCardapioInput;
import br.com.fiap.gastro_link_api_clean_architecture.infra.database.jpa.entity.EnderecoJpaEntity;
import br.com.fiap.gastro_link_api_clean_architecture.infra.database.jpa.entity.ItemCardapioJpaEntity;
import br.com.fiap.gastro_link_api_clean_architecture.infra.database.jpa.entity.RestauranteJpaEntity;
import br.com.fiap.gastro_link_api_clean_architecture.infra.database.jpa.entity.TipoCozinhaJpaEntity;
import br.com.fiap.gastro_link_api_clean_architecture.infra.database.jpa.entity.UsuarioJpaEntity;
import br.com.fiap.gastro_link_api_clean_architecture.infra.web.request.ItemCardapioRequest;
import br.com.fiap.gastro_link_api_clean_architecture.infra.web.response.ItemCardapioResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ItemCardapioMapperTest {

    @Test
    void toCadastrarItemCardapioInput_shouldMapAllFields() {
        ItemCardapioRequest req = new ItemCardapioRequest(
                "Pizza Margherita",
                "Deliciosa",
                new BigDecimal("25.50"),
                Boolean.TRUE,
                "/fotos/pizza.jpg",
                7L
        );

        CadastrarItemCardapioInput input = ItemCardapioMapper.toCadastrarItemCardapioInput(req);

        assertNotNull(input);
        assertEquals("Pizza Margherita", input.nome());
        assertEquals("Deliciosa", input.descricao());
        assertEquals(new BigDecimal("25.50"), input.preco());
        assertEquals(Boolean.TRUE, input.disponibilidadeApenasRestaurante());
        assertEquals("/fotos/pizza.jpg", input.caminhoFoto());
        assertEquals(7L, input.restauranteId());
    }

    @Test
    void toAtualizarItemCardapioInput_shouldMapAllFields() {
        ItemCardapioRequest req = new ItemCardapioRequest(
                "Sushi",
                "Fresco",
                new BigDecimal("40.00"),
                Boolean.FALSE,
                "/fotos/sushi.jpg",
                9L
        );

        AtualizarItemCardapioInput input = ItemCardapioMapper.toAtualizarItemCardapioInput(51L, req);

        assertNotNull(input);
        assertEquals(51L, input.id());
        assertEquals("Sushi", input.nome());
        assertEquals("Fresco", input.descricao());
        assertEquals(new BigDecimal("40.00"), input.preco());
        assertEquals(Boolean.FALSE, input.disponibilidadeApenasRestaurante());
        assertEquals("/fotos/sushi.jpg", input.caminhoFoto());
    }

    @Test
    void toItemCardapioResponse_shouldMapDomainToResponse() {
        TipoUsuario tipoUsuario = TipoUsuario.criar(2L, "Dono");
        Usuario dono = Usuario.criar(3L, "Carlos", "carlos@example.com", tipoUsuario, Boolean.TRUE);
        Endereco endereco = Endereco.criar(5L, "Av das Flores", "Apto 1", "100", "Centro", "CidadeX", "UF", "12345000");
        TipoCozinha tipoCozinha = TipoCozinha.criar(6L, "Italiana");
        Restaurante restaurante = Restaurante.criar(10L, "Restaurante X", endereco, tipoCozinha, "9-18", dono);

        ItemCardapio item = ItemCardapio.criar(99L, "Lasanha", "Caseira", new BigDecimal("30.00"), Boolean.FALSE, "/fotos/lasanha.jpg", restaurante);

        ItemCardapioResponse resp = ItemCardapioMapper.toItemCardapioResponse(item);

        assertNotNull(resp);
        assertEquals(99L, resp.id());
        assertEquals("Lasanha", resp.nome());
        assertEquals("Caseira", resp.descricao());
        assertEquals(new BigDecimal("30.00"), resp.preco());
        assertEquals(Boolean.FALSE, resp.disponibilidadeApenasRestaurante());
        assertEquals("/fotos/lasanha.jpg", resp.caminhoFoto());
        assertEquals(10L, resp.restauranteId());
    }

    @Test
    void toJpaEntity_and_toDomain_shouldMapAllFields() {
        // domain -> jpa
        TipoUsuario tipoUsuario = TipoUsuario.criar(2L, "Dono");
        Usuario dono = Usuario.criar(3L, "Carlos", "carlos@example.com", tipoUsuario, Boolean.TRUE);
        Endereco endereco = Endereco.criar(5L, "Av das Flores", "Apto 1", "100", "Centro", "CidadeX", "UF", "12345000");
        TipoCozinha tipoCozinha = TipoCozinha.criar(6L, "Italiana");
        Restaurante restaurante = Restaurante.criar(10L, "Restaurante X", endereco, tipoCozinha, "9-18", dono);
        ItemCardapio item = ItemCardapio.criar(99L, "Lasanha", "Caseira", new BigDecimal("30.00"), Boolean.FALSE, "/fotos/lasanha.jpg", restaurante);

        ItemCardapioJpaEntity jpa = ItemCardapioMapper.toJpaEntity(item);
        assertNotNull(jpa);
        assertEquals(99L, jpa.getId());
        assertEquals("Lasanha", jpa.getNome());
        assertEquals("Caseira", jpa.getDescricao());
        assertEquals(new BigDecimal("30.00"), jpa.getPreco());
        assertEquals(Boolean.FALSE, jpa.getDisponibilidadeApenasRestaurante());
        assertEquals("/fotos/lasanha.jpg", jpa.getCaminhoFoto());
        assertNotNull(jpa.getRestaurante());
        assertEquals(10L, jpa.getRestaurante().getId());

        // jpa -> domain
        ItemCardapioJpaEntity jpaEntity = new ItemCardapioJpaEntity();
        jpaEntity.setId(77L);
        jpaEntity.setNome("Bolo");
        jpaEntity.setDescricao("Doce");
        jpaEntity.setPreco(new BigDecimal("15.00"));
        jpaEntity.setDisponibilidadeApenasRestaurante(Boolean.TRUE);
        jpaEntity.setCaminhoFoto("/fotos/bolo.jpg");

        RestauranteJpaEntity rj = new RestauranteJpaEntity();
        rj.setId(21L);
        rj.setNome("Padaria");
        EnderecoJpaEntity ej = new EnderecoJpaEntity();
        ej.setId(31L);
        ej.setLogradouro("Rua Teste");
        ej.setNumero("1");
        ej.setBairro("Bairro");
        ej.setCidade("Cidade");
        ej.setUf("UF");
        ej.setCep("00000");
        rj.setEnderecoCompleto(ej);
        UsuarioJpaEntity uj = new UsuarioJpaEntity();
        uj.setId(41L);
        uj.setNome("Dono");
        uj.setEnderecoDeEmail("dono@ex.com");
        uj.setPossuiPermissaoDeDono(Boolean.TRUE);
        rj.setDono(uj);
        TipoCozinhaJpaEntity tcj = new TipoCozinhaJpaEntity();
        tcj.setId(51L);
        tcj.setNome("Confeitaria");
        rj.setTipoCozinha(tcj);

        jpaEntity.setRestaurante(rj);

        ItemCardapio domain = ItemCardapioMapper.toDomain(jpaEntity);
        assertNotNull(domain);
        assertEquals(77L, domain.getId());
        assertEquals("Bolo", domain.getNome());
        assertEquals("Doce", domain.getDescricao());
        assertEquals(new BigDecimal("15.00"), domain.getPreco());
        assertEquals(Boolean.TRUE, domain.getDisponibilidadeApenasRestaurante());
        assertEquals("/fotos/bolo.jpg", domain.getCaminhoFoto());
        assertNotNull(domain.getRestaurante());
        assertEquals(21L, domain.getRestaurante().getId());
    }

    @Test
    void mappingMethods_shouldHandleNull() {
        assertNull(ItemCardapioMapper.toCadastrarItemCardapioInput(null));
        assertNull(ItemCardapioMapper.toAtualizarItemCardapioInput(1L, null));
        assertNull(ItemCardapioMapper.toItemCardapioResponse(null));
    }
}