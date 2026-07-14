package br.com.fiap.gastro_link_api_clean_architecture.core.controller;

import br.com.fiap.gastro_link_api_clean_architecture.core.domain.Restaurante;
import br.com.fiap.gastro_link_api_clean_architecture.core.dto.CadastrarRestauranteInput;
import br.com.fiap.gastro_link_api_clean_architecture.core.dto.AtualizarRestauranteInput;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.IRestauranteGateway;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.IUsuarioGateway;
import br.com.fiap.gastro_link_api_clean_architecture.core.usecase.restaurante.CadastrarRestauranteUseCase;
import br.com.fiap.gastro_link_api_clean_architecture.core.usecase.restaurante.AtualizarRestauranteUseCase;
import br.com.fiap.gastro_link_api_clean_architecture.core.usecase.restaurante.BuscarRestauranteUseCase;
import br.com.fiap.gastro_link_api_clean_architecture.core.usecase.restaurante.DeletarRestauranteUseCase;

public class RestauranteController {

    private final IRestauranteGateway restauranteGateway;
    private final IUsuarioGateway usuarioGateway;

    private RestauranteController(IRestauranteGateway restauranteGateway, IUsuarioGateway usuarioGateway) {
        this.restauranteGateway = restauranteGateway;
        this.usuarioGateway = usuarioGateway;
    }

    public static RestauranteController criar(IRestauranteGateway restauranteGateway, IUsuarioGateway usuarioGateway) {
        return new RestauranteController(restauranteGateway, usuarioGateway);
    }

    public Restaurante cadastrar(CadastrarRestauranteInput cadastrarRestauranteInput) {
        return CadastrarRestauranteUseCase
                .criar(this.restauranteGateway, this.usuarioGateway)
                .processar(cadastrarRestauranteInput);
    }

    public Restaurante atualizar(AtualizarRestauranteInput atualizarRestauranteInput) {
        return AtualizarRestauranteUseCase
                .criar(this.restauranteGateway, this.usuarioGateway)
                .processar(atualizarRestauranteInput);
    }

    public Restaurante buscar(Long idRestaurante) {
        return BuscarRestauranteUseCase
                .criar(this.restauranteGateway)
                .processar(idRestaurante);
    }

    public void deletar(Long idRestaurante) {
        DeletarRestauranteUseCase
                .criar(this.restauranteGateway)
                .processar(idRestaurante);
    }
}
