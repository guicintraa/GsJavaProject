package com.futureskills.infra;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import java.sql.SQLException;
import java.util.Map;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {
    @Override public Response toResponse(Throwable ex) {

        if (ex instanceof NotFoundException)
            return Response.status(404).entity(Map.of("error","Not Found")).build();

        if (ex instanceof BadRequestException)
            return Response.status(400).entity(Map.of("error", ex.getMessage())).build();

        if (ex instanceof SQLException sql) {
            String msg = String.valueOf(sql.getMessage());

            if (msg.contains("ORA-00001"))
                return Response.status(409).entity(Map.of("error","E-mail já cadastrado","details", msg)).build();

            if (msg.contains("ORA-00942"))
                return Response.status(500).entity(Map.of("error","Tabela não encontrada no schema (rode o CRIA.sql em rm562850).","details", msg)).build();

            if (msg.contains("ORA-01017"))
                return Response.status(500).entity(Map.of("error","Usuário/senha Oracle inválidos.","details", msg)).build();

            if (msg.contains("ORA-125") || msg.contains("ORA-12170"))
                return Response.status(500).entity(Map.of("error","Conexão/serviço Oracle (URL/SID/ServiceName).","details", msg)).build();

            return Response.status(500).entity(Map.of("error","SQL Error","details", msg)).build();
        }

        ex.printStackTrace();
        return Response.status(500).entity(Map.of("error","Internal Server Error","details", ex.getMessage())).build();
    }
}
