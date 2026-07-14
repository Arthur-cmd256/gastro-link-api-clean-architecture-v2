package br.com.fiap.gastro_link_api_clean_architecture.infra.web.response;

import java.math.BigDecimal;

public record ItemCardapioResponse(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        Boolean disponibilidadeApenasRestaurante,
        String caminhoFoto,
        Long restauranteId
) {
}
