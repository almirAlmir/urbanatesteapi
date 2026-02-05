package com.apirest.cartao_service.infrastructure.entities;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tb_cartao")
public class CartaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_cartao", nullable = false, unique = true)
    private String numeroCartao;

    @Column(nullable = false)
    private String nome;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(nullable = false)
    private String tipo; // COMUM, ESTUDANTE, TRABALHADOR

    @Column(nullable = false)
    private Boolean status = true; // Padrão: TRUE (Ativo)

    public CartaoEntity() {
    }

    public CartaoEntity(String numeroCartao, String nome, Long usuarioId, String tipo) {
        this.numeroCartao = numeroCartao;
        this.nome = nome;
        this.usuarioId = usuarioId;
        this.tipo = tipo;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumeroCartao() { return numeroCartao; }
    public void setNumeroCartao(String numeroCartao) { this.numeroCartao = numeroCartao; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CartaoEntity that = (CartaoEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
