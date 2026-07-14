package br.com.fiap.gastro_link_api_clean_architecture.infra.database.jpa;

import br.com.fiap.gastro_link_api_clean_architecture.core.domain.Usuario;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.IUsuarioGateway;
import br.com.fiap.gastro_link_api_clean_architecture.infra.database.jpa.entity.UsuarioJpaEntity;
import br.com.fiap.gastro_link_api_clean_architecture.infra.database.jpa.repository.SpringUsuarioRepository;
import br.com.fiap.gastro_link_api_clean_architecture.infra.database.mapper.UsuarioMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioJpaGateway implements IUsuarioGateway {

    private final SpringUsuarioRepository springRepository;

    public UsuarioJpaGateway(SpringUsuarioRepository springRepository) {
        this.springRepository = springRepository;
    }

    @Override
    public Optional<Usuario> buscarUsuarioPorId(Long idUsuario) {
        return springRepository.findById(idUsuario).map(UsuarioMapper::toDomain);
    }

    @Override
    public Usuario salvarUsuario(Usuario usuario) {
        UsuarioJpaEntity jpaEntity = UsuarioMapper.toJpaEntity(usuario);
        UsuarioJpaEntity saved = springRepository.save(jpaEntity);
        return UsuarioMapper.toDomain(saved);
    }
}

