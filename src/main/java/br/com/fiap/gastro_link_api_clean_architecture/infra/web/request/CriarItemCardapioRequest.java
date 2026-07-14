package br.com.fiap.gastro_link_api_clean_architecture.infra.web.request;

import java.math.BigDecimal;

public record CriarItemCardapioRequest(
        String nome,
        String descricao,
        BigDecimal preco,
        Boolean disponibilidadeApenasRestaurante,
        String caminhoFoto,
        Long restauranteId
) {
}
