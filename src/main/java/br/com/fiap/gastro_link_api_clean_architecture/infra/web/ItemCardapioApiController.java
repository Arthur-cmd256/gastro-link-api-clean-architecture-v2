package br.com.fiap.gastro_link_api_clean_architecture.infra.web;

import br.com.fiap.gastro_link_api_clean_architecture.core.controller.ItemCardapioController;
import br.com.fiap.gastro_link_api_clean_architecture.core.domain.ItemCardapio;
import br.com.fiap.gastro_link_api_clean_architecture.core.dto.CadastrarItemCardapioInput;
import br.com.fiap.gastro_link_api_clean_architecture.core.dto.AtualizarItemCardapioInput;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.IItemCardapioGateway;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.IRestauranteGateway;
import br.com.fiap.gastro_link_api_clean_architecture.infra.database.mapper.ItemCardapioMapper;
import br.com.fiap.gastro_link_api_clean_architecture.infra.web.request.CriarItemCardapioRequest;
import br.com.fiap.gastro_link_api_clean_architecture.infra.web.response.ItemCardapioResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/itens-cardapio")
public class ItemCardapioApiController {

    private final IItemCardapioGateway itemCardapioGateway;
    private final IRestauranteGateway restauranteGateway;

    public ItemCardapioApiController(IItemCardapioGateway itemCardapioGateway, IRestauranteGateway restauranteGateway) {
        this.itemCardapioGateway = itemCardapioGateway;
        this.restauranteGateway = restauranteGateway;
    }

    @PostMapping
    public ResponseEntity<ItemCardapioResponse> criarItemCardapio(@RequestBody CriarItemCardapioRequest request) {
        CadastrarItemCardapioInput input = ItemCardapioMapper.toCadastrarItemCardapioInput(request);
        ItemCardapio item = ItemCardapioController.criar(this.itemCardapioGateway, this.restauranteGateway).cadastrar(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(ItemCardapioMapper.toItemCardapioResponse(item));
    }

    @GetMapping("/restaurante/{restauranteId}")
    public ResponseEntity<List<ItemCardapioResponse>> listarPorRestaurante(@PathVariable Long restauranteId) {
        List<ItemCardapio> itens = ItemCardapioController.criar(this.itemCardapioGateway, this.restauranteGateway).buscarPorRestaurante(restauranteId);
        List<ItemCardapioResponse> responses = itens.stream().map(ItemCardapioMapper::toItemCardapioResponse).collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemCardapioResponse> atualizarItemCardapio(@PathVariable Long id, @RequestBody CriarItemCardapioRequest request) {
        AtualizarItemCardapioInput input = ItemCardapioMapper.toAtualizarItemCardapioInput(id, request);
        ItemCardapio item = ItemCardapioController.criar(this.itemCardapioGateway, this.restauranteGateway).atualizar(input);
        return ResponseEntity.ok(ItemCardapioMapper.toItemCardapioResponse(item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarItemCardapio(@PathVariable Long id) {
        ItemCardapioController.criar(this.itemCardapioGateway, this.restauranteGateway).deletar(id);
        return ResponseEntity.noContent().build();
    }
}
