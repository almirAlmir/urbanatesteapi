package com.apirest.usuario.business.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UsuarioRequestDto {

    @JsonProperty(required = true)
    private String nome;
    @JsonProperty(required = true)
    private String email;
    @JsonProperty(required = true)
    private String senha;

    public UsuarioRequestDto(){

    }
    public UsuarioRequestDto(String nome, String email, String senha){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
