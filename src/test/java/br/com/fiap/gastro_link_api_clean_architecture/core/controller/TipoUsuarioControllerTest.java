package br.com.fiap.gastro_link_api_clean_architecture.core.controller;

import br.com.fiap.gastro_link_api_clean_architecture.core.domain.TipoUsuario;
import br.com.fiap.gastro_link_api_clean_architecture.core.dto.AtualizarTipoUsuarioInputDTO;
import br.com.fiap.gastro_link_api_clean_architecture.core.dto.CadastrarTipoUsuarioInputDTO;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.ITipoUsuarioGateway;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TipoUsuarioControllerTest {

    static class FakeGateway implements ITipoUsuarioGateway {
        Long lastSavedId;
        String lastSavedName;
        Long lastDeletedId;

        @Override
        public TipoUsuario salvarTipoUsuario(TipoUsuario tipoUsuario) {
            this.lastSavedName = tipoUsuario.getNome();
            if (tipoUsuario.getId() != null) {
                this.lastSavedId = tipoUsuario.getId();
            } else {
                this.lastSavedId = 42L;
            }
            return TipoUsuario.criar(this.lastSavedId, tipoUsuario.getNome());
        }

        @Override
        public Optional<TipoUsuario> buscarTipoUsuarioPorId(Long id) {
            return Optional.of(TipoUsuario.criar(id, "Tipo-" + id));
        }

        @Override
        public TipoUsuario deletarTipoUsuario(Long id) {
            this.lastDeletedId = id;
            return TipoUsuario.criar(id, "Deleted");
        }
    }

    @Test
    void cadastrar_and_buscar_and_atualizar_and_deletar_flow() {
        FakeGateway gateway = new FakeGateway();
        TipoUsuarioController controller = TipoUsuarioController.criar(gateway);

        CadastrarTipoUsuarioInputDTO cadastrar = new CadastrarTipoUsuarioInputDTO("Cliente");
        var out = controller.cadastrar(cadastrar);
        assertNotNull(out);
        assertEquals(42L, out.id());
        assertEquals("Cliente", out.nome());

        var buscado = controller.buscar(7L);
        assertNotNull(buscado);
        assertEquals(7L, buscado.id());

        AtualizarTipoUsuarioInputDTO atualizar = new AtualizarTipoUsuarioInputDTO(7L, "NovoNome");
        var updated = controller.atualizar(atualizar);
        assertNotNull(updated);
        assertEquals(7L, updated.id());

        controller.deletar(9L);
        assertEquals(9L, gateway.lastDeletedId);
    }
}