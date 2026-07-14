package br.com.fiap.gastro_link_api_clean_architecture.core.controller;

import br.com.fiap.gastro_link_api_clean_architecture.core.domain.TipoUsuario;
import br.com.fiap.gastro_link_api_clean_architecture.core.dto.CadastrarTipoUsuarioInputDTO;
import br.com.fiap.gastro_link_api_clean_architecture.core.dto.CadastrarTipoUsuarioOutDTO;
import br.com.fiap.gastro_link_api_clean_architecture.core.dto.AtualizarTipoUsuarioInputDTO;
import br.com.fiap.gastro_link_api_clean_architecture.core.gateway.ITipoUsuarioGateway;
import br.com.fiap.gastro_link_api_clean_architecture.core.mapper.TipoEstudantePresenter;
import br.com.fiap.gastro_link_api_clean_architecture.core.usecase.tipousuario.CadastrarTipoUsuarioUseCase;
import br.com.fiap.gastro_link_api_clean_architecture.core.usecase.tipousuario.AtualizarTipoUsuarioUseCase;
import br.com.fiap.gastro_link_api_clean_architecture.core.usecase.tipousuario.BuscarTipoUsuarioUseCase;
import br.com.fiap.gastro_link_api_clean_architecture.core.usecase.tipousuario.DeletarTipoUsuarioUseCase;

public class TipoUsuarioController {

    private final ITipoUsuarioGateway tipoUsuarioGateway;

    private TipoUsuarioController(ITipoUsuarioGateway tipoUsuarioGateway) {
        this.tipoUsuarioGateway = tipoUsuarioGateway;
    }

    public static TipoUsuarioController criar(ITipoUsuarioGateway tipoUsuarioGateway) {
        return new TipoUsuarioController(tipoUsuarioGateway);
    }

    public CadastrarTipoUsuarioOutDTO cadastrar(CadastrarTipoUsuarioInputDTO cadastrarTipoUsuarioInputDTO) {
        TipoUsuario tipoUsuario = CadastrarTipoUsuarioUseCase
                .criar(this.tipoUsuarioGateway)
                .processar(cadastrarTipoUsuarioInputDTO.nome());
        return TipoEstudantePresenter.mapToCadastrarTipoUsuarioOutDTO(tipoUsuario);
    }

    public CadastrarTipoUsuarioOutDTO atualizar(AtualizarTipoUsuarioInputDTO atualizarTipoUsuarioInputDTO) {
        TipoUsuario tipoUsuario = AtualizarTipoUsuarioUseCase
                .criar(this.tipoUsuarioGateway)
                .processar(atualizarTipoUsuarioInputDTO);
        return TipoEstudantePresenter.mapToCadastrarTipoUsuarioOutDTO(tipoUsuario);
    }

    public CadastrarTipoUsuarioOutDTO buscar(Long idTipoUsuario) {
        TipoUsuario tipoUsuario = BuscarTipoUsuarioUseCase
                .criar(this.tipoUsuarioGateway)
                .processar(idTipoUsuario);
        return TipoEstudantePresenter.mapToCadastrarTipoUsuarioOutDTO(tipoUsuario);
    }

    public void deletar(Long idTipoUsuario) {
        DeletarTipoUsuarioUseCase
                .criar(this.tipoUsuarioGateway)
                .processar(idTipoUsuario);
    }

}
