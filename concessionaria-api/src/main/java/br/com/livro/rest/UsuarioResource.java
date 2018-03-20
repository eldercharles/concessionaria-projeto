package br.com.livro.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.livro.domain.Response;
import br.com.livro.domain.Usuario;
import br.com.livro.domain.UsuarioService;

@Path("/user")
@Produces({"application/json"})
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class UsuarioResource {
	private UsuarioService usuarioService = new UsuarioService();
	
	@GET
	public List<Usuario> all() {
		List<Usuario> usuarios = usuarioService.getUsuarios();
		return usuarios;
	}
	
	@GET
	@Path("{id}")
	public Usuario teste(@PathParam("id") long id) {
		Usuario u = usuarioService.getUsuario(id);
		return u;
	}
	

	@POST
	@Path("/autenticar")
	public Usuario autenticar(Usuario usuario) {
		Usuario u = usuarioService.autenticar(usuario);
		return u;
	}
	
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") long id) {
		usuarioService.delete(id);
		return Response.Ok("Usuario deletado com sucesso");
	}

	@POST
	public Response post(Usuario u) {
		usuarioService.save(u);
		return Response.Ok("Usuario salvo com sucesso");
	}

	@PUT
	public Response put(Usuario u) {
		usuarioService.save(u);
		return Response.Ok("Usuario atualizado com sucesso");
	}
	

}
