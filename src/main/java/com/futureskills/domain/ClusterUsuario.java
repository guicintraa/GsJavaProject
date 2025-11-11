package com.futureskills.domain;

import java.util.Date;

public class ClusterUsuario {
    private Long idCluster;
    private Long idUsuario;
    private Integer clusterLabel;
    private String descricaoCluster;
    private Date dtAtribuicao;

    public Long getIdCluster() { return idCluster; }
    public void setIdCluster(Long idCluster) { this.idCluster = idCluster; }

    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }

    public Integer getClusterLabel() { return clusterLabel; }
    public void setClusterLabel(Integer clusterLabel) { this.clusterLabel = clusterLabel; }

    public String getDescricaoCluster() { return descricaoCluster; }
    public void setDescricaoCluster(String descricaoCluster) { this.descricaoCluster = descricaoCluster; }

    public Date getDtAtribuicao() { return dtAtribuicao; }
    public void setDtAtribuicao(Date dtAtribuicao) { this.dtAtribuicao = dtAtribuicao; }
}
