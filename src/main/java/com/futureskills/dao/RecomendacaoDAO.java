package com.futureskills.dao;

import com.futureskills.domain.Recomendacao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@ApplicationScoped
public class RecomendacaoDAO {

    @Inject
    DataSource dataSource;

    public void inserir(Recomendacao r) throws Exception {
        String sql = "INSERT INTO T_RECOMENDACAO (ID_USUARIO, DS_CARREIRA, DS_TRILHA, SCORE_MODELO) VALUES (?, ?, ?, ?)";
        try (Connection c = dataSource.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, r.getIdUsuario());
            ps.setString(2, r.getCarreira());
            ps.setString(3, r.getTrilha());
            ps.setDouble(4, r.getScoreModelo());
            ps.executeUpdate();
        }
    }

    public List<Recomendacao> listarPorUsuario(Long idUsuario) throws Exception {
        String sql = "SELECT ID_RECOMENDACAO, ID_USUARIO, DS_CARREIRA, DS_TRILHA, SCORE_MODELO, DT_RECOMENDACAO " +
                     "FROM T_RECOMENDACAO WHERE ID_USUARIO=? ORDER BY DT_RECOMENDACAO DESC";
        List<Recomendacao> lista = new ArrayList<>();
        try (Connection c = dataSource.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Recomendacao r = new Recomendacao();
                    r.setIdRecomendacao(rs.getLong("ID_RECOMENDACAO"));
                    r.setIdUsuario(rs.getLong("ID_USUARIO"));
                    r.setCarreira(rs.getString("DS_CARREIRA"));
                    r.setTrilha(rs.getString("DS_TRILHA"));
                    r.setScoreModelo(rs.getDouble("SCORE_MODELO"));
                    r.setDtRecomendacao(rs.getTimestamp("DT_RECOMENDACAO"));
                    lista.add(r);
                }
            }
        }
        return lista;
    }
}
