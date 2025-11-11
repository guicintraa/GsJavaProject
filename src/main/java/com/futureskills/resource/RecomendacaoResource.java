package com.futureskills.resource;

import com.futureskills.domain.Recomendacao;
import com.futureskills.service.RecomendacaoService;
import com.futureskills.dao.RecomendacaoDAO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/recomendacao")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RecomendacaoResource {

    @Inject
    RecomendacaoService service;

    @Inject
    RecomendacaoDAO dao;

    @POST
    @Path("/{idUsuario}")
    public Recomendacao gerar(@PathParam("idUsuario") Long idUsuario) throws Exception {
        return service.gerar(idUsuario);
    }

    @GET
    @Path("/usuario/{idUsuario}")
    public List<Recomendacao> historico(@PathParam("idUsuario") Long idUsuario) throws Exception {
        return dao.listarPorUsuario(idUsuario);
    }
}
