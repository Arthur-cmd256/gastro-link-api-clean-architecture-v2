package br.com.fiap.gastro_link_api_clean_architecture.infra.web.response;

public record CriarRestauranteResponse(
        Long id,
        String nome,
        Long donoId,
        EnderecoResponse endereco,
        String tipoCozinha,
        String horarioFuncionamento
) {
}
