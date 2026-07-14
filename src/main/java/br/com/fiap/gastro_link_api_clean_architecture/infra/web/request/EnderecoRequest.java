package br.com.fiap.gastro_link_api_clean_architecture.infra.web.request;

public record EnderecoRequest(
        String logradouro,
        String complemento,
        String numero,
        String bairro,
        String cidade,
        String uf,
        String cep
) {
}
