package br.com.fiap.gastro_link_api_clean_architecture.infra.web;

import br.com.fiap.gastro_link_api_clean_architecture.core.controller.RestauranteController;
import br.com.fiap.gastro_link_api_clean_architecture.core.domain.Restaurante;
import br.com.fiap.gastro_link_api_clean_architecture.core.dto.CadastrarRestauranteInput;
import br.com.fiap.gastro_link_api_clean_architecture.core.dto.AtualizarRestauranteInput;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.IRestauranteGateway;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.IUsuarioGateway;
import br.com.fiap.gastro_link_api_clean_architecture.infra.database.mapper.RestauranteMapper;
import br.com.fiap.gastro_link_api_clean_architecture.infra.web.request.RestauranteRequest;
import br.com.fiap.gastro_link_api_clean_architecture.infra.web.response.RestauranteResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/restaurantes")
public class RestauranteApiController {

    private final IRestauranteGateway restauranteGateway;
    private final IUsuarioGateway usuarioGateway;

    public RestauranteApiController(IRestauranteGateway restauranteGateway, IUsuarioGateway usuarioGateway) {
        this.restauranteGateway = restauranteGateway;
        this.usuarioGateway = usuarioGateway;
    }

    @PostMapping
    public ResponseEntity<RestauranteResponse> criarRestaurante(@RequestBody RestauranteRequest request) {
        CadastrarRestauranteInput input = RestauranteMapper.toCadastrarRestauranteInput(request);
        Restaurante restaurante = RestauranteController.criar(this.restauranteGateway, this.usuarioGateway).cadastrar(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(RestauranteMapper.toCriarRestauranteResponse(restaurante));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestauranteResponse> buscarRestaurante(@PathVariable Long id) {
        Restaurante restaurante = RestauranteController.criar(this.restauranteGateway, this.usuarioGateway).buscar(id);
        return ResponseEntity.ok(RestauranteMapper.toCriarRestauranteResponse(restaurante));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestauranteResponse> atualizarRestaurante(@PathVariable Long id, @RequestBody RestauranteRequest request) {
        AtualizarRestauranteInput input = RestauranteMapper.toAtualizarRestauranteInput(id, request);
        Restaurante restaurante = RestauranteController.criar(this.restauranteGateway, this.usuarioGateway).atualizar(input);
        return ResponseEntity.ok(RestauranteMapper.toCriarRestauranteResponse(restaurante));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRestaurante(@PathVariable Long id) {
        RestauranteController.criar(this.restauranteGateway, this.usuarioGateway).deletar(id);
        return ResponseEntity.noContent().build();
    }
}
