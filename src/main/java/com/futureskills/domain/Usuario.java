package com.futureskills.domain;

import java.util.Date;

public class Usuario {
    private Long idUsuario;
    private String nome;
    private String email;
    private String interesse;
    private Date dtCriacao;

    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getInteresse() { return interesse; }
    public void setInteresse(String interesse) { this.interesse = interesse; }

    public Date getDtCriacao() { return dtCriacao; }
    public void setDtCriacao(Date dtCriacao) { this.dtCriacao = dtCriacao; }
}
