package br.com.fiap.gastro_link_api_clean_architecture.core.domain;

import br.com.fiap.gastro_link_api_clean_architecture.core.exception.DadoObrigatorioException;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ItemCardapio {
    private final Long id;
    private final String nome;
    private final String descricao;
    private final BigDecimal preco;
    private final Boolean disponibilidadeApenasRestaurante;
    private final String caminhoFoto;
    private final Restaurante restaurante;

    private ItemCardapio(Long id, String nome, String descricao, BigDecimal preco, Boolean disponibilidadeApenasRestaurante, String caminhoFoto, Restaurante restaurante) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.disponibilidadeApenasRestaurante = disponibilidadeApenasRestaurante;
        this.caminhoFoto = caminhoFoto;
        this.restaurante = restaurante;
    }

    public static ItemCardapio criar(String nome, String descricao, BigDecimal preco, Boolean disponibilidadeApenasRestaurante, String caminhoFoto, Restaurante restaurante) {
        return new ItemCardapio(null, nome, descricao, preco, disponibilidadeApenasRestaurante, caminhoFoto, restaurante);
    }

    public static ItemCardapio criar(Long idItemCardapio,String nome, String descricao, BigDecimal preco, Boolean disponibilidadeApenasRestaurante, String caminhoFoto, Restaurante restaurante) {
        return new ItemCardapio(idItemCardapio, nome, descricao, preco, disponibilidadeApenasRestaurante, caminhoFoto, restaurante);
    }

    public String getNomeRestaurante() {
        return restaurante.getNome();
    }
}
