package com.futureskills.dao;

import com.futureskills.domain.ClusterUsuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import javax.sql.DataSource;
import java.sql.*;

@ApplicationScoped
public class ClusterUsuarioDAO {

    @Inject
    DataSource dataSource;

    public void inserir(ClusterUsuario cinfo) throws Exception {
        String sql = "INSERT INTO T_CLUSTER_USUARIO (ID_USUARIO, CLUSTER_LABEL, DS_CLUSTER) VALUES (?, ?, ?)";
        try (Connection c = dataSource.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, cinfo.getIdUsuario());
            ps.setInt(2, cinfo.getClusterLabel());
            ps.setString(3, cinfo.getDescricaoCluster());
            ps.executeUpdate();
        }
    }

    public ClusterUsuario buscarPorUsuario(Long idUsuario) throws Exception {
        String sql = "SELECT ID_CLUSTER, ID_USUARIO, CLUSTER_LABEL, DS_CLUSTER, DT_ATRIBUICAO " +
                     "FROM T_CLUSTER_USUARIO WHERE ID_USUARIO=? ORDER BY DT_ATRIBUICAO DESC FETCH FIRST 1 ROWS ONLY";
        try (Connection c = dataSource.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ClusterUsuario cu = new ClusterUsuario();
                    cu.setIdCluster(rs.getLong("ID_CLUSTER"));
                    cu.setIdUsuario(rs.getLong("ID_USUARIO"));
                    cu.setClusterLabel(rs.getInt("CLUSTER_LABEL"));
                    cu.setDescricaoCluster(rs.getString("DS_CLUSTER"));
                    cu.setDtAtribuicao(rs.getTimestamp("DT_ATRIBUICAO"));
                    return cu;
                }
                return null;
            }
        }
    }
}
