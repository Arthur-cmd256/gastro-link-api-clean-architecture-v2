package br.com.fiap.gastro_link_api_clean_architecture.core.dto;

import java.math.BigDecimal;

public record CadastrarItemCardapioInput(
        String nome,
        String descricao,
        BigDecimal preco,
        Boolean disponibilidadeApenasRestaurante,
        String caminhoFoto,
        Long restauranteId
) {
}
