package com.futureskills.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.inject.Inject;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@Path("/api/debug")
@Produces(MediaType.APPLICATION_JSON)
public class DebugResource {
    @Inject DataSource ds;

    @GET @Path("/db")
    public Map<String,Object> db() throws Exception {
        Map<String,Object> out = new LinkedHashMap<>();
        try (Connection c = ds.getConnection()) {
            out.put("connected", true);

            // 1) SELECT 1
            try (PreparedStatement ps = c.prepareStatement("SELECT 1 FROM DUAL");
                 ResultSet rs = ps.executeQuery()) {
                rs.next();
                out.put("select1", rs.getInt(1));
            }

            // 2) Conferir se T_USUARIO existe no SCHEMA atual
            boolean existeUsuario;
            try (PreparedStatement ps = c.prepareStatement(
                    "SELECT COUNT(*) FROM user_tables WHERE table_name = 'T_USUARIO'");
                 ResultSet rs = ps.executeQuery()) {
                rs.next();
                existeUsuario = rs.getInt(1) > 0;
            }
            out.put("table_T_USUARIO_exists", existeUsuario);

            // 3) Se existir, conta linhas
            if (existeUsuario) {
                try (PreparedStatement ps = c.prepareStatement("SELECT COUNT(*) FROM T_USUARIO");
                     ResultSet rs = ps.executeQuery()) {
                    rs.next();
                    out.put("T_USUARIO_count", rs.getInt(1));
                }
            }
            return out;
        } catch (Exception e) {
            out.put("connected", false);
            out.put("error", e.getClass().getName());
            out.put("details", String.valueOf(e.getMessage()));
            return out;
        }
    }
}
