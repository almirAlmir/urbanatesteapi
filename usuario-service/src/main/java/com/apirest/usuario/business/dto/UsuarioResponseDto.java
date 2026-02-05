package com.apirest.usuario.business.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UsuarioResponseDto {

    @JsonProperty(required = true)
    private String nome;
    @JsonProperty(required = true)
    private String email;
    @JsonProperty(required = true)
    private String tipo;

    public UsuarioResponseDto(){

    }
    public UsuarioResponseDto(String nome, String email, String tipo){
        this.nome = nome;
        this.email = email;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
