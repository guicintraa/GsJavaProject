package com.futureskills.domain;

import java.util.Date;

public class IaLog {
    private Long idLog;
    private Long idUsuario;
    private String modelo;
    private String resultado;
    private Double probabilidade;
    private Date dtExecucao;

    public Long getIdLog() { return idLog; }
    public void setIdLog(Long idLog) { this.idLog = idLog; }

    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getResultado() { return resultado; }
    public void setResultado(String resultado) { this.resultado = resultado; }

    public Double getProbabilidade() { return probabilidade; }
    public void setProbabilidade(Double probabilidade) { this.probabilidade = probabilidade; }

    public Date getDtExecucao() { return dtExecucao; }
    public void setDtExecucao(Date dtExecucao) { this.dtExecucao = dtExecucao; }
}
