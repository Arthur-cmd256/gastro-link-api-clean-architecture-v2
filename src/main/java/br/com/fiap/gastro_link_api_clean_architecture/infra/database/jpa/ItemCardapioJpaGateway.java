package br.com.fiap.gastro_link_api_clean_architecture.infra.database.jpa;

import br.com.fiap.gastro_link_api_clean_architecture.core.domain.ItemCardapio;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.IItemCardapioGateway;
import br.com.fiap.gastro_link_api_clean_architecture.infra.database.jpa.entity.ItemCardapioJpaEntity;
import br.com.fiap.gastro_link_api_clean_architecture.infra.database.jpa.repository.SpringItemCardapioRepository;
import br.com.fiap.gastro_link_api_clean_architecture.infra.database.mapper.ItemCardapioMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ItemCardapioJpaGateway implements IItemCardapioGateway {

    private final SpringItemCardapioRepository springRepository;

    public ItemCardapioJpaGateway(SpringItemCardapioRepository springRepository) {
        this.springRepository = springRepository;
    }

    @Override
    public ItemCardapio salvarItemCardapio(ItemCardapio itemCardapio) {
        ItemCardapioJpaEntity jpaEntity = ItemCardapioMapper.toJpaEntity(itemCardapio);
        ItemCardapioJpaEntity saved = springRepository.save(jpaEntity);
        return ItemCardapioMapper.toDomain(saved);
    }

    @Override
    public List<ItemCardapio> buscarItensCardapioPorRestauranteId(Long idRestaurante) {
        return springRepository.findByRestauranteId(idRestaurante)
                .stream()
                .map(ItemCardapioMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ItemCardapio> buscarItemCardapioPorId(Long id) {
        return springRepository.findById(id).map(ItemCardapioMapper::toDomain);
    }

    @Override
    public void deletarItemCardapio(Long idItemCardapio) {
        springRepository.deleteById(idItemCardapio);
    }
}

