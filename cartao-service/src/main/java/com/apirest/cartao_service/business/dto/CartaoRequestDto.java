package com.apirest.cartao_service.business.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CartaoRequestDto {

    @JsonProperty(required = true)
    private Long usuarioId;

    @JsonProperty(required = true)
    private String nome;

    @JsonProperty(required = true)
    private String tipo;

    // Construtor vazio
    public CartaoRequestDto() {
    }

    public CartaoRequestDto(Long usuarioId, String nome, String tipo) {
        this.usuarioId = usuarioId;
        this.nome = nome;
        this.tipo = tipo;
    }

    // Getters e Setters
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}
