package br.com.fiap.gastro_link_api_clean_architecture.infra.database.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tb_usuario")
public class UsuarioJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String enderecoDeEmail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_usuario")
    private TipoUsuarioJpaEntity tipoUsuario;

    @OneToMany(mappedBy = "dono", fetch = FetchType.LAZY)
    private Set<RestauranteJpaEntity> restaurantes;

    @Column(nullable = false)
    private Boolean possuiPermissaoDeDono;

    public UsuarioJpaEntity() {
    }
}