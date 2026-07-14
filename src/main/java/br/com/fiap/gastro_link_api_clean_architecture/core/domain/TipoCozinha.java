package br.com.fiap.gastro_link_api_clean_architecture.core.domain;

import lombok.Getter;

@Getter
public class TipoCozinha {
    private final Long id;
    private final String nome;

    private TipoCozinha(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public static TipoCozinha criar(String nome){
        return new TipoCozinha(null, nome);
    }

    public static TipoCozinha criar(Long id, String nome){
        return new TipoCozinha(id, nome);
    }

}
