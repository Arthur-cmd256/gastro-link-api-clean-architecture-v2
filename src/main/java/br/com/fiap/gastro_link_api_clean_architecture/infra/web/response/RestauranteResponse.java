package br.com.fiap.gastro_link_api_clean_architecture.infra.web.response;

public record RestauranteResponse(
        Long id,
        String nome,
        Long donoId,
        EnderecoResponse endereco,
        String tipoCozinha,
        String horarioFuncionamento
) {
}
