package br.com.fiap.gastro_link_api_clean_architecture.core.factory;

import br.com.fiap.gastro_link_api_clean_architecture.core.domain.*;

public class UsuarioFactory {
    public static Usuario criarDonoPadrao() {
        TipoUsuario tipoUsuario = TipoUsuario.criar(1L, "Dono");
        return Usuario.criar(1L, "João", "joao@teste.com", tipoUsuario, true);
    }
}
