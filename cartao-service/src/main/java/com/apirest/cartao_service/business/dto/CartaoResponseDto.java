package com.apirest.cartao_service.business.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CartaoResponseDto {

    @JsonProperty(required = true)
    private Long id;

    @JsonProperty(required = true)
    private String numeroCartao;

    @JsonProperty(required = true)
    private String nome;

    @JsonProperty(required = true)
    private Long usuarioId;

    @JsonProperty(required = true)
    private String tipo;

    @JsonProperty(required = true)
    private Boolean status;

    public CartaoResponseDto() {
    }

    public CartaoResponseDto(Long id, String numeroCartao, String nome, Long usuarioId, String tipo, Boolean status) {
        this.id = id;
        this.numeroCartao = numeroCartao;
        this.nome = nome;
        this.usuarioId = usuarioId;
        this.tipo = tipo;
        this.status = status;
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
}
