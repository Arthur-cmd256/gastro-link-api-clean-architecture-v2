package br.com.fiap.gastro_link_api_clean_architecture.core.domain;

import lombok.Getter;

@Getter
public class Usuario {
    private final Long id;
    private final String nome;
    private final String enderecoDeEmail;
    private final TipoUsuario tipoUsuario;
    private final Boolean possuiPermissaoDeDono;

    private Usuario(Long id, String nome, String enderecoDeEmail, TipoUsuario tipoUsuario, Boolean possuiPermissaoDeDono) {
        this.id = id;
        this.nome = nome;
        this.enderecoDeEmail = enderecoDeEmail;
        this.tipoUsuario = tipoUsuario;
        this.possuiPermissaoDeDono = possuiPermissaoDeDono;
    }

    public static Usuario criar(String nome, String enderecoDeEmail, TipoUsuario tipoUsuario, Boolean possuiPermissaoDeDono) {
        return new Usuario(null, nome, enderecoDeEmail, tipoUsuario, possuiPermissaoDeDono);
    }

    public static Usuario criar(Long id, String nome, String enderecoDeEmail, TipoUsuario tipoUsuario, Boolean possuiPermissaoDeDono) {
        return new Usuario(id, nome, enderecoDeEmail, tipoUsuario, possuiPermissaoDeDono);
    }

    public String getTipoUsuarioNome() {
        return tipoUsuario.getNome();
    }
}
