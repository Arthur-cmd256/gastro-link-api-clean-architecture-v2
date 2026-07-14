package br.com.fiap.gastro_link_api_clean_architecture.infra.database.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tb_tipo_cozinha")
public class TipoCozinhaJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @OneToMany(mappedBy = "tipoCozinha")
    private Set<RestauranteJpaEntity> restaurantes;
}