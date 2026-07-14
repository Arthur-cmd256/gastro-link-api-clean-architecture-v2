package br.com.fiap.gastro_link_api_clean_architecture.infra.web;

import br.com.fiap.gastro_link_api_clean_architecture.core.controller.TipoUsuarioController;
import br.com.fiap.gastro_link_api_clean_architecture.core.dto.CadastrarTipoUsuarioInputDTO;
import br.com.fiap.gastro_link_api_clean_architecture.core.dto.CadastrarTipoUsuarioOutDTO;
import br.com.fiap.gastro_link_api_clean_architecture.core.dto.AtualizarTipoUsuarioInputDTO;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.ITipoUsuarioGateway;
import br.com.fiap.gastro_link_api_clean_architecture.infra.database.mapper.TipoUsuarioMapper;
import br.com.fiap.gastro_link_api_clean_architecture.infra.web.request.CriarTipoUsuarioRequest;
import br.com.fiap.gastro_link_api_clean_architecture.infra.web.response.CriarTipoUsuarioResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/v1/tipos-usuario")
public class TipoUsuarioApiController {

    private final ITipoUsuarioGateway tipoUsuarioGateway;

    public TipoUsuarioApiController(ITipoUsuarioGateway tipoUsuarioGateway) {
        this.tipoUsuarioGateway = tipoUsuarioGateway;
    }

    @PostMapping
    public ResponseEntity<CriarTipoUsuarioResponse> criarTipoUsuario(@RequestBody CriarTipoUsuarioRequest request) {
        CadastrarTipoUsuarioInputDTO inputDTO = TipoUsuarioMapper.toCadastrarTipoUsuarioInputDTO(request);
        CadastrarTipoUsuarioOutDTO outDTO = TipoUsuarioController
                .criar(this.tipoUsuarioGateway)
                .cadastrar(inputDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(TipoUsuarioMapper.toCriarTipoUsuarioResponse(outDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CriarTipoUsuarioResponse> buscarTipoUsuario(@PathVariable Long id) {
        CadastrarTipoUsuarioOutDTO outDTO = TipoUsuarioController
                .criar(this.tipoUsuarioGateway)
                .buscar(id);
        return ResponseEntity.ok(TipoUsuarioMapper.toCriarTipoUsuarioResponse(outDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CriarTipoUsuarioResponse> atualizarTipoUsuario(@PathVariable Long id, @RequestBody CriarTipoUsuarioRequest request) {
        AtualizarTipoUsuarioInputDTO inputDTO = TipoUsuarioMapper.toAtualizarTipoUsuarioInputDTO(id, request);
        CadastrarTipoUsuarioOutDTO outDTO = TipoUsuarioController
                .criar(this.tipoUsuarioGateway)
                .atualizar(inputDTO);
        return ResponseEntity.ok(TipoUsuarioMapper.toCriarTipoUsuarioResponse(outDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTipoUsuario(@PathVariable Long id) {
        TipoUsuarioController.criar(this.tipoUsuarioGateway).deletar(id);
        return ResponseEntity.noContent().build();
    }
}
