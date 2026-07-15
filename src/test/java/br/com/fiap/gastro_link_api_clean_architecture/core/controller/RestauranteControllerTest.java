package br.com.fiap.gastro_link_api_clean_architecture.core.controller;

import br.com.fiap.gastro_link_api_clean_architecture.core.domain.Endereco;
import br.com.fiap.gastro_link_api_clean_architecture.core.domain.Restaurante;
import br.com.fiap.gastro_link_api_clean_architecture.core.domain.TipoCozinha;
import br.com.fiap.gastro_link_api_clean_architecture.core.domain.TipoUsuario;
import br.com.fiap.gastro_link_api_clean_architecture.core.domain.Usuario;
import br.com.fiap.gastro_link_api_clean_architecture.core.dto.AtualizarRestauranteInput;
import br.com.fiap.gastro_link_api_clean_architecture.core.dto.CadastrarRestauranteInput;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.IRestauranteGateway;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.IUsuarioGateway;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class RestauranteControllerTest {

    static class FakeRestauranteGateway implements IRestauranteGateway {
        Restaurante lastSaved;
        Long lastDeleted;

        @Override
        public Restaurante salvarRestaurante(Restaurante restaurante) {
            this.lastSaved = Restaurante.criar(99L, restaurante.getNome(), restaurante.getEndereco(), restaurante.getTipoCozinha(), restaurante.getHorarioFuncionamento(), restaurante.getDono());
            return this.lastSaved;
        }

        @Override
        public Optional<Restaurante> buscarRestaurantePorId(Long idRestaurante) {
            return Optional.of(Restaurante.criar(idRestaurante, "R-" + idRestaurante, null, TipoCozinha.criar(5L, "X"), "9-17", Usuario.criar(7L, "Dono", "dono@example.com", TipoUsuario.criar(2L, "Dono"), Boolean.TRUE)));
        }

        @Override
        public void deletarRestaurante(Long idRestaurante) {
            this.lastDeleted = idRestaurante;
        }
    }

    static class FakeUsuarioGateway implements IUsuarioGateway {
        @Override
        public Optional<Usuario> buscarUsuarioPorId(Long idUsuario) {
            return Optional.of(Usuario.criar(idUsuario, "User" + idUsuario, "u@example.com", TipoUsuario.criar(2L, "Dono"), Boolean.TRUE));
        }

        @Override
        public Usuario salvarUsuario(Usuario usuario) {
            return usuario;
        }
    }

    @Test
    void cadastrar_buscar_atualizar_deletar_flow() {
        FakeRestauranteGateway rg = new FakeRestauranteGateway();
        FakeUsuarioGateway ug = new FakeUsuarioGateway();
        RestauranteController controller = RestauranteController.criar(rg, ug);

        Endereco end = Endereco.criar(null, "Logradouro", "Compl", "10", "Bairro", "Cidade", "UF", "00000-000");
        CadastrarRestauranteInput cadastrar = new CadastrarRestauranteInput("Novo", 7L, end, TipoCozinha.criar("Italiana"), "9-22");

        Restaurante created = controller.cadastrar(cadastrar);
        assertNotNull(created);
        assertEquals(99L, created.getId());

        Restaurante fetched = controller.buscar(33L);
        assertNotNull(fetched);
        assertEquals(33L, fetched.getId());

        AtualizarRestauranteInput atualizar = new AtualizarRestauranteInput(99L, "NomeAtualizado", end, TipoCozinha.criar("Italiana"), "10-20", 7L);
        Restaurante updated = controller.atualizar(atualizar);
        assertNotNull(updated);
        assertEquals(99L, updated.getId());

        controller.deletar(99L);
        assertEquals(99L, rg.lastDeleted);
    }
}
