package br.com.fiap.gastro_link_api_clean_architecture.core.factory;


import br.com.fiap.gastro_link_api_clean_architecture.core.domain.*;

public class RestauranteFactory {

    public static Restaurante criarRestaurantePadrao() {
        TipoUsuario tipoUsuario = TipoUsuario.criar(1L, "Dono");
        Usuario dono = Usuario.criar(1L, "João", "joao@teste.com", tipoUsuario);

        Endereco endereco = Endereco.criar(
                "Av Paulista", "1000", "A", "Centro", "SP", "SP", "04184-000"
        );

        TipoCozinha tipoCozinha = TipoCozinha.criar(1L, "Brasileira");

        return Restaurante.criar(
                1L,
                "Restaurante Teste",
                endereco,
                tipoCozinha,
                "10:00 - 22:00",
                dono
        );
    }

    public static Restaurante criarRestauranteComId(Long idRestaurante) {
        TipoUsuario tipoUsuario = TipoUsuario.criar(1L, "Dono");
        Usuario dono = Usuario.criar(1L, "João", "joao@teste.com", tipoUsuario);

        Endereco endereco = Endereco.criar(
                "Av Paulista", "1000", "A", "Centro", "SP", "SP", "04184-000"
        );

        TipoCozinha tipoCozinha = TipoCozinha.criar(1L, "Brasileira");

        return Restaurante.criar(
                idRestaurante,
                "Restaurante Teste",
                endereco,
                tipoCozinha,
                "10:00 - 22:00",
                dono
        );
    }
}
