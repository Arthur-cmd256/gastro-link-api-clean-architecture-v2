package br.com.fiap.gastro_link_api_clean_architecture.infra.database.mapper;

import br.com.fiap.gastro_link_api_clean_architecture.core.domain.ItemCardapio;
import br.com.fiap.gastro_link_api_clean_architecture.core.dto.CadastrarItemCardapioInput;
import br.com.fiap.gastro_link_api_clean_architecture.core.dto.AtualizarItemCardapioInput;
import br.com.fiap.gastro_link_api_clean_architecture.infra.database.jpa.entity.ItemCardapioJpaEntity;
import br.com.fiap.gastro_link_api_clean_architecture.infra.web.request.ItemCardapioRequest;
import br.com.fiap.gastro_link_api_clean_architecture.infra.web.response.ItemCardapioResponse;

public class ItemCardapioMapper {

    public static ItemCardapioJpaEntity toJpaEntity(ItemCardapio itemCardapio) {
        if (itemCardapio == null) return null;

        ItemCardapioJpaEntity jpaEntity = new ItemCardapioJpaEntity();
        jpaEntity.setId(itemCardapio.getId());
        jpaEntity.setNome(itemCardapio.getNome());
        jpaEntity.setDescricao(itemCardapio.getDescricao());
        jpaEntity.setPreco(itemCardapio.getPreco());
        jpaEntity.setDisponibilidadeApenasRestaurante(itemCardapio.getDisponibilidadeApenasRestaurante());
        jpaEntity.setCaminhoFoto(itemCardapio.getCaminhoFoto());
        jpaEntity.setRestaurante(RestauranteMapper.toJpaEntity(itemCardapio.getRestaurante()));

        return jpaEntity;
    }

    public static ItemCardapio toDomain(ItemCardapioJpaEntity jpaEntity) {
        if (jpaEntity == null) return null;

        return ItemCardapio.criar(
                jpaEntity.getId(),
                jpaEntity.getNome(),
                jpaEntity.getDescricao(),
                jpaEntity.getPreco(),
                jpaEntity.getDisponibilidadeApenasRestaurante(),
                jpaEntity.getCaminhoFoto(),
                RestauranteMapper.toDomain(jpaEntity.getRestaurante())
        );
    }

    public static CadastrarItemCardapioInput toCadastrarItemCardapioInput(ItemCardapioRequest request) {
        if (request == null) return null;
        return new CadastrarItemCardapioInput(
                request.nome(),
                request.descricao(),
                request.preco(),
                request.disponibilidadeApenasRestaurante(),
                request.caminhoFoto(),
                request.restauranteId()
        );
    }

    public static AtualizarItemCardapioInput toAtualizarItemCardapioInput(Long id, ItemCardapioRequest request) {
        if (request == null) return null;
        return new AtualizarItemCardapioInput(
                id,
                request.nome(),
                request.descricao(),
                request.preco(),
                request.disponibilidadeApenasRestaurante(),
                request.caminhoFoto()
        );
    }

    public static ItemCardapioResponse toItemCardapioResponse(ItemCardapio item) {
        if (item == null) return null;
        return new ItemCardapioResponse(
                item.getId(),
                item.getNome(),
                item.getDescricao(),
                item.getPreco(),
                item.getDisponibilidadeApenasRestaurante(),
                item.getCaminhoFoto(),
                item.getRestaurante().getId()
        );
    }
}
