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

import br.com.livro.domain.Carro;
import br.com.livro.domain.CarroService;
import br.com.livro.domain.Response;

// CONSULTAR E INSERIR OS CARROS NO BANCO DE DADOS

@Path("/concessionaria-api")
// @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces({"application/json"})
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class CarrosResource {
	private CarroService carroService = new CarroService();
	/*private Carro carro = new Carro()*/;

	
	@GET
	public List<Carro> all() {
		List<Carro> carros = carroService.getCarros();
		return carros;
	}
	
	@GET
	@Path("{cod_veiculo}")
	public Carro teste(@PathParam("cod_veiculo") long cod_veiculo) {
		Carro c = carroService.getCarro(cod_veiculo);
		return c;
	}
	
	@GET
	@Path("/fabricantes")
	public List<String> getFabricantes() {
		List<String> fabricantes = carroService.getFabricantes();
		return fabricantes;
	}

	@GET
	@Path("/modelos/{fabricante}")
	public List<String> getModelosByFabricante(@PathParam("fabricante") String fabricante) {
		List<String> modelos = carroService.getModelosByFabricante(fabricante);
		return modelos;
	}
	@GET
	@Path("/fabricante/{fabricante}")
	public List<Carro> getByTipo(@PathParam("fabricante") String fabricante) {
		List<Carro> carros = carroService.findByFabricante(fabricante);
		return carros;
	}

	@GET
	@Path("/modelo/{modelo}")
	public List<Carro> getByModelo(@PathParam("modelo") String modelo) {
		List<Carro> carros = carroService.findByModelo(modelo);
		return carros;
	}

	@DELETE
	@Path("{cod_veiculo}")
	public Response delete(@PathParam("cod_veiculo") long cod_veiculo) {
		carroService.delete(cod_veiculo);
		return Response.Ok("Carro deletado com sucesso");
	}
	
	@POST
	public Response post(Carro c) {
		carroService.save(c);
		return Response.Ok("Carro salvo com sucesso");
	}

	@PUT
	public Response put(Carro c) {
		carroService.save(c);
		return Response.Ok("Carro atualizado com sucesso");
	}
	
//	@POST
//	public Response post(Carro c) {
//		try {
//			carroService.save(c);		
//		}catch (Exception e) {
//			e.printStackTrace();
//			return Response.Error("Erro");
//		}
//		return Response.Ok("Carro inserido com sucesso");
//	}
//	@PUT
//	public Response put(Carro c) {
//		try {
//			carroService.save(c);		
//		}catch (Exception e) {
//			e.printStackTrace();
//			return Response.Error("Erro");
//		}
//		return Response.Ok("Carro atualizado com sucesso");
//	}
}
