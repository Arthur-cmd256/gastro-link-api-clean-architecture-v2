package br.com.fiap.gastro_link_api_clean_architecture.core.domain;


import br.com.fiap.gastro_link_api_clean_architecture.core.exception.DadoObrigatorioException;
import br.com.fiap.gastro_link_api_clean_architecture.core.exception.RestauranteDeveTerDonoException;
import lombok.Getter;

@Getter
public class Restaurante {
    private final Long id;
    private final String nome;
    private final Endereco endereco;
    private final TipoCozinha tipoCozinha;
    private final String horarioFuncionamento;
    private final Usuario dono;

    private Restaurante(Long id, String nome, Endereco endereco, TipoCozinha tipoCozinha, String horarioFuncionamento, Usuario dono) {
        if (nome == null || nome.isBlank()) {
            throw new DadoObrigatorioException("Nome do restaurante");
        }
        if (dono == null) {
            throw new RestauranteDeveTerDonoException();
        }

        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.tipoCozinha = tipoCozinha;
        this.horarioFuncionamento = horarioFuncionamento;
        this.dono = dono;
    }

    public static Restaurante criar(String nome, Endereco endereco, TipoCozinha tipoCozinha, String horarioFuncionamento, Usuario dono ) {
        return new Restaurante(null, nome, endereco, tipoCozinha, horarioFuncionamento, dono);
    }

    public static Restaurante criar(Long id, String nome, Endereco endereco, TipoCozinha tipoCozinha, String horarioFuncionamento, Usuario dono ) {
        return new Restaurante(id, nome, endereco, tipoCozinha, horarioFuncionamento, dono);
    }

    public String getNomeDono() {
        return dono.getNome();
    }

    public String getNomeTipoCozinha() {
        return tipoCozinha.getNome();
    }

}
