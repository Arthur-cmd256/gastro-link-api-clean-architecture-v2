package br.com.fiap.gastro_link_api_clean_architecture.core.usecase.restaurante;


import br.com.fiap.gastro_link_api_clean_architecture.core.domain.Restaurante;
import br.com.fiap.gastro_link_api_clean_architecture.core.domain.Usuario;
import br.com.fiap.gastro_link_api_clean_architecture.core.dto.CadastrarRestauranteInput;
import br.com.fiap.gastro_link_api_clean_architecture.core.exception.UsuarioNaoEncontradoException;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.IRestauranteGateway;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.IUsuarioGateway;

public class CadastrarRestauranteUseCase {
    private final IRestauranteGateway restauranteGateway;
    private final IUsuarioGateway usuarioGateway;

    private CadastrarRestauranteUseCase(IRestauranteGateway restauranteGateway, IUsuarioGateway usuarioGateway) {
        this.restauranteGateway = restauranteGateway;
        this.usuarioGateway = usuarioGateway;
    }

    public static CadastrarRestauranteUseCase criar(IRestauranteGateway restauranteGateway, IUsuarioGateway usuarioGateway) {
        return new CadastrarRestauranteUseCase(restauranteGateway, usuarioGateway);
    }

    public Restaurante processar(CadastrarRestauranteInput input) {
        Usuario dono = this.usuarioGateway
                .buscarUsuarioPorId(input.donoId())
                .orElseThrow(() -> new UsuarioNaoEncontradoException(input.donoId()));
        Restaurante restaurante = Restaurante.criar(
                input.nome(),
                input.endereco(),
                input.tipoCozinha(),
                input.horarioFuncionamento(),
                dono
        );
        return this.restauranteGateway.salvarRestaurante(restaurante);
    }
}
