package com.futureskills.resource;

import com.futureskills.dao.ClusterUsuarioDAO;
import com.futureskills.domain.ClusterUsuario;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/api/cluster")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClusterResource {

    @Inject
    ClusterUsuarioDAO dao;

    @GET
    @Path("/usuario/{idUsuario}")
    public ClusterUsuario atual(@PathParam("idUsuario") Long idUsuario) throws Exception {
        return dao.buscarPorUsuario(idUsuario);
    }
}
