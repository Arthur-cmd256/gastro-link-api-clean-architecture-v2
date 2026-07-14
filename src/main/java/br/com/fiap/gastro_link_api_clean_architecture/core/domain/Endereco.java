package br.com.fiap.gastro_link_api_clean_architecture.core.domain;

import lombok.Getter;

@Getter
public class Endereco {
    private final Long id;
    private final String logradouro;
    private final String numero;
    private final String complemento;
    private final String bairro;
    private final String cidade;
    private final String uf;
    private final String cep;

    private Endereco(Long id, String logradouro, String complemento, String numero, String bairro, String cidade, String uf, String cep) {
        this.id = id;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.cep = cep;
    }

    public static Endereco criar(String logradouro, String complemento, String numero, String bairro, String cidade, String uf, String cep){
        return new Endereco(null, logradouro, complemento, numero, bairro, cidade, uf, cep);
    }

}
