package br.com.fiap.gastro_link_api_clean_architecture.core.factory;

import br.com.fiap.gastro_link_api_clean_architecture.core.domain.*;

public class EnderecoFactory {
    public static Endereco criarEnderecoPadrao() {

        return Endereco.criar(
                "Avenida Paulista",
                "1001",
                "Torre B",
                "Boa vista",
                "São Paulo",
                "SP",
                "01310-100"
        );
    }
}
