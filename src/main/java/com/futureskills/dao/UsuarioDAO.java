package com.futureskills.dao;

import com.futureskills.domain.Usuario;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@ApplicationScoped
public class UsuarioDAO {

    @Inject
    DataSource dataSource;

    public void inserir(Usuario u) throws Exception {
        String sql = "INSERT INTO T_USUARIO (NM_USUARIO, DS_EMAIL, DS_INTERESSE) VALUES (?, ?, ?)";
        try (Connection c = dataSource.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, u.getNome());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getInteresse());
            ps.executeUpdate();
        }
    }

    public Usuario buscarPorId(Long id) throws Exception {
        String sql = "SELECT ID_USUARIO, NM_USUARIO, DS_EMAIL, DS_INTERESSE, DT_CRIACAO FROM T_USUARIO WHERE ID_USUARIO=?";
        try (Connection c = dataSource.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario u = new Usuario();
                    u.setIdUsuario(rs.getLong("ID_USUARIO"));
                    u.setNome(rs.getString("NM_USUARIO"));
                    u.setEmail(rs.getString("DS_EMAIL"));
                    u.setInteresse(rs.getString("DS_INTERESSE"));
                    u.setDtCriacao(rs.getDate("DT_CRIACAO"));
                    return u;
                }
                return null;
            }
        }
    }

    public List<Usuario> listar() throws Exception {
        String sql = "SELECT ID_USUARIO, NM_USUARIO, DS_EMAIL, DS_INTERESSE, DT_CRIACAO FROM T_USUARIO ORDER BY ID_USUARIO";
        List<Usuario> lista = new ArrayList<>();
        try (Connection c = dataSource.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getLong("ID_USUARIO"));
                u.setNome(rs.getString("NM_USUARIO"));
                u.setEmail(rs.getString("DS_EMAIL"));
                u.setInteresse(rs.getString("DS_INTERESSE"));
                u.setDtCriacao(rs.getDate("DT_CRIACAO"));
                lista.add(u);
            }
        }
        return lista;
    }
}
