package com.futureskills.domain;

import java.util.Date;

public class Recomendacao {
    private Long idRecomendacao;
    private Long idUsuario;
    private String carreira;
    private String trilha;
    private Double scoreModelo;
    private Date dtRecomendacao;

    public Long getIdRecomendacao() { return idRecomendacao; }
    public void setIdRecomendacao(Long idRecomendacao) { this.idRecomendacao = idRecomendacao; }

    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }

    public String getCarreira() { return carreira; }
    public void setCarreira(String carreira) { this.carreira = carreira; }

    public String getTrilha() { return trilha; }
    public void setTrilha(String trilha) { this.trilha = trilha; }

    public Double getScoreModelo() { return scoreModelo; }
    public void setScoreModelo(Double scoreModelo) { this.scoreModelo = scoreModelo; }

    public Date getDtRecomendacao() { return dtRecomendacao; }
    public void setDtRecomendacao(Date dtRecomendacao) { this.dtRecomendacao = dtRecomendacao; }
}
