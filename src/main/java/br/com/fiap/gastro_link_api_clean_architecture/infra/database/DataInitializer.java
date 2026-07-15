package br.com.fiap.gastro_link_api_clean_architecture.infra.database;

import br.com.fiap.gastro_link_api_clean_architecture.core.domain.TipoUsuario;
import br.com.fiap.gastro_link_api_clean_architecture.core.domain.Usuario;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.ITipoUsuarioGateway;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.IUsuarioGateway;
import br.com.fiap.gastro_link_api_clean_architecture.infra.database.jpa.repository.SpringUsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final SpringUsuarioRepository usuarioRepository;
    private final ITipoUsuarioGateway tipoUsuarioGateway;
    private final IUsuarioGateway usuarioGateway;

    public DataInitializer(SpringUsuarioRepository usuarioRepository, ITipoUsuarioGateway tipoUsuarioGateway, IUsuarioGateway usuarioGateway) {
        this.usuarioRepository = usuarioRepository;
        this.tipoUsuarioGateway = tipoUsuarioGateway;
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.count() > 0) return; // já inicializado

        // criar tipos de usuario
        TipoUsuario donoType = tipoUsuarioGateway.salvarTipoUsuario(TipoUsuario.criar("Dono"));
        TipoUsuario clienteType = tipoUsuarioGateway.salvarTipoUsuario(TipoUsuario.criar("Cliente"));
        TipoUsuario estudanteType = tipoUsuarioGateway.salvarTipoUsuario(TipoUsuario.criar("Estudante"));

        // criar usuarios iniciais
        Usuario dono = Usuario.criar("João Dono", "joao.dono@example.com", donoType, true);
        Usuario cliente = Usuario.criar("Maria Cliente", "maria.cliente@example.com", clienteType, false);
        Usuario estudante = Usuario.criar("Pedro Estudante", "pedro.estudante@example.com", estudanteType, false);

        usuarioGateway.salvarUsuario(dono);
        usuarioGateway.salvarUsuario(cliente);
        usuarioGateway.salvarUsuario(estudante);
    }
}
