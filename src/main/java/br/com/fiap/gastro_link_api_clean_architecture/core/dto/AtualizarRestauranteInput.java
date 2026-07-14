package br.com.fiap.gastro_link_api_clean_architecture.core.dto;


import br.com.fiap.gastro_link_api_clean_architecture.core.domain.Endereco;
import br.com.fiap.gastro_link_api_clean_architecture.core.domain.TipoCozinha;

public record AtualizarRestauranteInput(
        Long id,
        String nome,
        Endereco endereco,
        TipoCozinha tipoCozinha,
        String horarioFuncionamento,
        Long donoId
) {
}
