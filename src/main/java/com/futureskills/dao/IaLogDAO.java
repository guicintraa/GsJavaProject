package com.futureskills.dao;

import com.futureskills.domain.IaLog;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import javax.sql.DataSource;
import java.sql.*;

@ApplicationScoped
public class IaLogDAO {

    @Inject
    DataSource dataSource;

    public void inserir(IaLog log) throws Exception {
        String sql = "INSERT INTO T_MODELO_IA_LOG (ID_USUARIO, NM_MODELO, DS_RESULTADO, PROBABILIDADE) VALUES (?, ?, ?, ?)";
        try (Connection c = dataSource.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, log.getIdUsuario());
            ps.setString(2, log.getModelo());
            ps.setString(3, log.getResultado());
            ps.setDouble(4, log.getProbabilidade());
            ps.executeUpdate();
        }
    }
}
