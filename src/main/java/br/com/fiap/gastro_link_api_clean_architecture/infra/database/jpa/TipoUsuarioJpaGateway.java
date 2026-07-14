package br.com.fiap.gastro_link_api_clean_architecture.infra.database.jpa;

import br.com.fiap.gastro_link_api_clean_architecture.core.domain.TipoUsuario;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.ITipoUsuarioGateway;
import br.com.fiap.gastro_link_api_clean_architecture.infra.database.jpa.entity.TipoUsuarioJpaEntity;
import br.com.fiap.gastro_link_api_clean_architecture.infra.database.jpa.repository.SpringTipoUsuarioRepository;
import br.com.fiap.gastro_link_api_clean_architecture.infra.database.mapper.TipoUsuarioMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TipoUsuarioJpaGateway implements ITipoUsuarioGateway {

    private final SpringTipoUsuarioRepository springRepository;

    public TipoUsuarioJpaGateway(SpringTipoUsuarioRepository springRepository) {
        this.springRepository = springRepository;
    }

    @Override
    public TipoUsuario salvarTipoUsuario(TipoUsuario tipoUsuario) {
        TipoUsuarioJpaEntity jpaEntity = TipoUsuarioMapper.toJpaEntity(tipoUsuario);
        TipoUsuarioJpaEntity saved = springRepository.save(jpaEntity);
        return TipoUsuarioMapper.toDomain(saved);
    }

    @Override
    public Optional<TipoUsuario> buscarTipoUsuarioPorId(Long id) {
        return springRepository.findById(id).map(TipoUsuarioMapper::toDomain);
    }

    @Override
    public TipoUsuario deletarTipoUsuario(Long id) {
        TipoUsuarioJpaEntity entity = springRepository.findById(id).orElse(null);
        if (entity != null) {
            springRepository.deleteById(id);
        }
        return TipoUsuarioMapper.toDomain(entity);
    }
}

