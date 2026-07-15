package br.com.fiap.gastro_link_api_clean_architecture.infra.database.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_restaurante")
@Getter
@Setter
public class RestauranteJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String horarioFuncionamento;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco", nullable = false)
    private EnderecoJpaEntity enderecoCompleto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dono", nullable = false)
    private UsuarioJpaEntity dono;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_tipo_cozinha", nullable = false)
    private TipoCozinhaJpaEntity tipoCozinha;

    @OneToMany(mappedBy = "restaurante", fetch = FetchType.LAZY)
    private java.util.Set<ItemCardapioJpaEntity> itensCardapio;


    // O JPA exige um construtor vazio
    public RestauranteJpaEntity() {}
}