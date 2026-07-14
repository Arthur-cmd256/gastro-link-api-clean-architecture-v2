package br.com.fiap.gastro_link_api_clean_architecture.infra.web.request;

public record CriarRestauranteRequest(
        String nome,
        Long donoId,
        EnderecoRequest endereco,
        String tipoCozinha,
        String horarioFuncionamento
) {
}
