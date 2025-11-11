package com.futureskills.service;

import com.futureskills.dao.*;
import com.futureskills.domain.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class RecomendacaoService {

    @Inject UsuarioDAO usuarioDAO;
    @Inject RecomendacaoDAO recomendacaoDAO;
    @Inject ClusterUsuarioDAO clusterDAO;
    @Inject IaLogDAO iaLogDAO;

    public Recomendacao gerar(Long idUsuario) throws Exception {
        Usuario u = usuarioDAO.buscarPorId(idUsuario);
        if (u == null) throw new NotFoundException("Usuário não existe.");

        String interesse = (u.getInteresse() == null ? "" : u.getInteresse().toLowerCase());
        String carreira = "PM";
        int cluster = 3;
        String trilha = "Fundamentos";

        switch (interesse) {
            case "data" -> { carreira = "Analista de Dados"; cluster = 1; trilha = "SQL; Python; Dashboards"; }
            case "tech" -> { carreira = "Desenvolvedor"; cluster = 0; trilha = "POO; Git; JavaScript"; }
            case "design" -> { carreira = "UX/UI"; cluster = 2; trilha = "Figma; Pesquisa; Prototipação"; }
            case "seguranca" -> { carreira = "Cybersegurança"; cluster = 4; trilha = "OWASP; IAM; Hardening"; }
            case "business" -> { carreira = "PM"; cluster = 3; trilha = "Scrum; OKR; Roadmap"; }
        }

        double score = 0.70 + (Math.random() * 0.2); // 0.70–0.90

        Recomendacao r = new Recomendacao();
        r.setIdUsuario(idUsuario);
        r.setCarreira(carreira);
        r.setTrilha(trilha);
        r.setScoreModelo(score);
        recomendacaoDAO.inserir(r);

        ClusterUsuario cu = new ClusterUsuario();
        cu.setIdUsuario(idUsuario);
        cu.setClusterLabel(cluster);
        cu.setDescricaoCluster(trilha);
        clusterDAO.inserir(cu);

        IaLog log = new IaLog();
        log.setIdUsuario(idUsuario);
        log.setModelo("RegraJava");
        log.setResultado(carreira + " / cluster=" + cluster);
        log.setProbabilidade(score);
        iaLogDAO.inserir(log);

        return r;
    }
}
