package br.com.fiap.gastro_link_api_clean_architecture.core.controller;

import br.com.fiap.gastro_link_api_clean_architecture.core.domain.Endereco;
import br.com.fiap.gastro_link_api_clean_architecture.core.domain.ItemCardapio;
import br.com.fiap.gastro_link_api_clean_architecture.core.domain.Restaurante;
import br.com.fiap.gastro_link_api_clean_architecture.core.domain.TipoCozinha;
import br.com.fiap.gastro_link_api_clean_architecture.core.domain.TipoUsuario;
import br.com.fiap.gastro_link_api_clean_architecture.core.domain.Usuario;
import br.com.fiap.gastro_link_api_clean_architecture.core.dto.AtualizarItemCardapioInput;
import br.com.fiap.gastro_link_api_clean_architecture.core.dto.CadastrarItemCardapioInput;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.IItemCardapioGateway;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.IRestauranteGateway;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ItemCardapioControllerTest {

    static class FakeItemGateway implements IItemCardapioGateway {
        ItemCardapio lastSaved;
        Long lastDeleted;

        @Override
        public ItemCardapio salvarItemCardapio(ItemCardapio itemCardapio) {
            this.lastSaved = ItemCardapio.criar(123L, itemCardapio.getNome(), itemCardapio.getDescricao(), itemCardapio.getPreco(), itemCardapio.getDisponibilidadeApenasRestaurante(), itemCardapio.getCaminhoFoto(), itemCardapio.getRestaurante());
            return this.lastSaved;
        }

        @Override
        public java.util.List<ItemCardapio> buscarItensCardapioPorRestauranteId(Long idRestaurante) {
            return List.of(ItemCardapio.criar(1L, "Item1", "Desc", new BigDecimal("10.00"), Boolean.FALSE, "/f.jpg", Restaurante.criar(idRestaurante, "R", null, TipoCozinha.criar(1L, "X"), "h", Usuario.criar(2L, "U", "e@e.com", TipoUsuario.criar(3L, "T"), Boolean.TRUE))));
        }

        @Override
        public Optional<ItemCardapio> buscarItemCardapioPorId(Long id) {
            return Optional.of(ItemCardapio.criar(id, "ItemX", "DescX", new BigDecimal("12.00"), Boolean.TRUE, "/f2.jpg", Restaurante.criar(5L, "R5", null, TipoCozinha.criar(1L, "X"), "h", Usuario.criar(2L, "U", "e@e.com", TipoUsuario.criar(3L, "T"), Boolean.TRUE))));
        }

        @Override
        public void deletarItemCardapio(Long idItemCardapio) {
            this.lastDeleted = idItemCardapio;
        }
    }

    static class FakeRestauranteGateway implements IRestauranteGateway {
        @Override
        public Restaurante salvarRestaurante(Restaurante restaurante) { return restaurante; }

        @Override
        public Optional<Restaurante> buscarRestaurantePorId(Long idRestaurante) {
            return Optional.of(Restaurante.criar(idRestaurante, "R", null, TipoCozinha.criar(1L, "X"), "h", Usuario.criar(2L, "U", "e@e.com", TipoUsuario.criar(3L, "T"), Boolean.TRUE)));
        }

        @Override
        public void deletarRestaurante(Long idRestaurante) { }
    }

    @Test
    void cadastrar_buscar_atualizar_deletar_flow() {
        FakeItemGateway ig = new FakeItemGateway();
        FakeRestauranteGateway rg = new FakeRestauranteGateway();
        ItemCardapioController controller = ItemCardapioController.criar(ig, rg);

        CadastrarItemCardapioInput cadastrar = new CadastrarItemCardapioInput("N", "D", new BigDecimal("5.00"), Boolean.FALSE, "/p.jpg", 5L);
        ItemCardapio created = controller.cadastrar(cadastrar);
        assertNotNull(created);
        assertEquals(123L, created.getId());

        var list = controller.buscarPorRestaurante(5L);
        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertEquals(1L, list.get(0).getId());

        AtualizarItemCardapioInput atualizar = new AtualizarItemCardapioInput(123L, "N2", "D2", new BigDecimal("6.00"), Boolean.TRUE, "/p2.jpg");
        ItemCardapio updated = controller.atualizar(atualizar);
        assertNotNull(updated);
        assertEquals(123L, updated.getId());

        controller.deletar(123L);
        assertEquals(123L, ig.lastDeleted);
    }
}
