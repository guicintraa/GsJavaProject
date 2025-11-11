package com.futureskills.resource;

import com.futureskills.dao.UsuarioDAO;
import com.futureskills.domain.Usuario;
import jakarta.inject.Inject;
import jakarta.validation.constraints.Email;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/api/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    UsuarioDAO dao;

    @POST
    public Response criar(Usuario u) throws Exception {
        if (u.getNome() == null || u.getNome().isBlank())
            throw new BadRequestException("Nome é obrigatório.");
        if (u.getEmail() == null || !u.getEmail().contains("@"))
            throw new BadRequestException("Email inválido.");
        dao.inserir(u);
        return Response.status(Response.Status.CREATED).entity(u).build();
    }

    @GET
    public List<Usuario> listar() throws Exception {
        return dao.listar();
    }

    @GET
    @Path("/{id}")
    public Usuario obter(@PathParam("id") Long id) throws Exception {
        Usuario u = dao.buscarPorId(id);
        if (u == null) throw new NotFoundException();
        return u;
    }
}
