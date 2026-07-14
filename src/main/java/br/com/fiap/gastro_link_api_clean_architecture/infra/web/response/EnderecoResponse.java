package br.com.fiap.gastro_link_api_clean_architecture.infra.web.response;

public record EnderecoResponse(
        Long id,
        String logradouro,
        String complemento,
        String numero,
        String bairro,
        String cidade,
        String uf,
        String cep
) {
}
