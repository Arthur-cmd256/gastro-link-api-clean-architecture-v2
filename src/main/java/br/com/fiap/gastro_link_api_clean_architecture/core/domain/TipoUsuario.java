package br.com.fiap.gastro_link_api_clean_architecture.core.domain;

import lombok.Getter;

@Getter
public class TipoUsuario {
    private final Long id;
    private final String nome;

    private TipoUsuario(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public static TipoUsuario criar(String nome) {
        return new TipoUsuario(null, nome);
    }

    public static TipoUsuario criar(Long id, String nome) {
        return new TipoUsuario(id, nome);
    }

}
