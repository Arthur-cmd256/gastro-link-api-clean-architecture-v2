package br.com.fiap.gastro_link_api_clean_architecture.infra.database.mapper;

import br.com.fiap.gastro_link_api_clean_architecture.core.domain.Endereco;
import br.com.fiap.gastro_link_api_clean_architecture.core.domain.Restaurante;
import br.com.fiap.gastro_link_api_clean_architecture.core.domain.TipoCozinha;
import br.com.fiap.gastro_link_api_clean_architecture.core.dto.AtualizarRestauranteInput;
import br.com.fiap.gastro_link_api_clean_architecture.core.dto.CadastrarRestauranteInput;
import br.com.fiap.gastro_link_api_clean_architecture.infra.database.jpa.entity.RestauranteJpaEntity;
import br.com.fiap.gastro_link_api_clean_architecture.infra.web.request.RestauranteRequest;
import br.com.fiap.gastro_link_api_clean_architecture.infra.web.request.EnderecoRequest;
import br.com.fiap.gastro_link_api_clean_architecture.infra.web.response.RestauranteResponse;
import br.com.fiap.gastro_link_api_clean_architecture.infra.web.response.EnderecoResponse;

public class RestauranteMapper {

    public static RestauranteJpaEntity toJpaEntity(Restaurante restaurante) {
        if (restaurante == null) return null;

        RestauranteJpaEntity jpaEntity = new RestauranteJpaEntity();
        jpaEntity.setId(restaurante.getId());
        jpaEntity.setNome(restaurante.getNome());
        jpaEntity.setHorarioFuncionamento(restaurante.getHorarioFuncionamento());
        jpaEntity.setEnderecoCompleto(EnderecoMapper.toJpaEntity(restaurante.getEndereco()));
        jpaEntity.setDono(UsuarioMapper.toJpaEntity(restaurante.getDono()));
        jpaEntity.setTipoCozinha(TipoCozinhaMapper.toJpaEntity(restaurante.getTipoCozinha()));

        return jpaEntity;
    }

    public static Restaurante toDomain(RestauranteJpaEntity restauranteJpaEntity) {
        if (restauranteJpaEntity == null) return null;

        return Restaurante.criar(
                restauranteJpaEntity.getId(),
                restauranteJpaEntity.getNome(),
                EnderecoMapper.toDomain(restauranteJpaEntity.getEnderecoCompleto()),
                TipoCozinhaMapper.toDomain(restauranteJpaEntity.getTipoCozinha()),
                restauranteJpaEntity.getHorarioFuncionamento(),
                UsuarioMapper.toDomain(restauranteJpaEntity.getDono())
        );
    }

    public static CadastrarRestauranteInput toCadastrarRestauranteInput(RestauranteRequest request) {
        if (request == null) return null;
        EnderecoRequest er = request.endereco();
        Endereco endereco = Endereco.criar(er.logradouro(), er.complemento(), er.numero(), er.bairro(), er.cidade(), er.uf(), er.cep());
        TipoCozinha tipoCozinha = TipoCozinha.criar(request.tipoCozinha());
        return new CadastrarRestauranteInput(
                request.nome(),
                request.donoId(),
                endereco,
                tipoCozinha,
                request.horarioFuncionamento()
        );
    }

    public static AtualizarRestauranteInput toAtualizarRestauranteInput(Long id, RestauranteRequest request) {
        if (request == null) return null;
        EnderecoRequest er = request.endereco();
        Endereco endereco = Endereco.criar(er.logradouro(), er.complemento(), er.numero(), er.bairro(), er.cidade(), er.uf(), er.cep());
        TipoCozinha tipoCozinha = TipoCozinha.criar(request.tipoCozinha());
        return new AtualizarRestauranteInput(
                id,
                request.nome(),
                endereco,
                tipoCozinha,
                request.horarioFuncionamento(),
                request.donoId()
        );
    }

    public static RestauranteResponse toCriarRestauranteResponse(Restaurante restaurante) {
        if (restaurante == null) return null;
        var endereco = restaurante.getEndereco();
        EnderecoResponse enderecoResponse = new EnderecoResponse(
                endereco.getId(),
                endereco.getLogradouro(),
                endereco.getComplemento(),
                endereco.getNumero(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getUf(),
                endereco.getCep()
        );
        return new RestauranteResponse(
                restaurante.getId(),
                restaurante.getNome(),
                restaurante.getDono().getId(),
                enderecoResponse,
                restaurante.getTipoCozinha().getNome(),
                restaurante.getHorarioFuncionamento()
        );
    }

}
