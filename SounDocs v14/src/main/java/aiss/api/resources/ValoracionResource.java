package aiss.api.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.jboss.resteasy.spi.BadRequestException;
import org.jboss.resteasy.spi.NotFoundException;

import aiss.api.model.resource.comparators.ComparatorValPuntuacion;
import aiss.api.model.resource.comparators.ComparatorValPuntuacionRev;
import aiss.model.Valoracion;
import aiss.model.repository.MapValoracionRepository;
import aiss.model.repository.ValoracionRepository;

@Path("/valoraciones")
public class ValoracionResource {

	public static ValoracionResource _instance=null;
	ValoracionRepository repository;

	public ValoracionResource(){
		repository = MapValoracionRepository.getInstance();
	}

	public static ValoracionResource getInstance()	{
		if(_instance == null)
			_instance = new ValoracionResource();
		return _instance; 
	}

	@GET
	@Produces("application/json")
	public Collection<Valoracion> getAll(@QueryParam("order") String order, @QueryParam("nota") String nota, @QueryParam("q") String q){
		List<Valoracion> result = new ArrayList<Valoracion>();

		for (Valoracion valoracion: repository.getAllValoraciones()) {
			if (q == null || valoracion.getNombre().toLowerCase().contains(q.toLowerCase()))
				result.add(valoracion);
		}
		
		if (order != null) { // Order results
            if (order.equals("-puntuacion")) {
                Collections.sort(result, new ComparatorValPuntuacion());
            } else if (order.equals("+puntuacion")) {
            	Collections.sort(result, new ComparatorValPuntuacionRev());
            } else {
                throw new BadRequestException("The order parameter must be 'puntuacion' or '-puntuacion'.");
            }
        }

        return result;
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Valoracion get(@PathParam("id") String vId){
		Valoracion list = repository.getValoracion(vId);
		if (list == null) {
			throw new NotFoundException("La valoracion con el id = "+ vId +" no fue encontrada");			
		}

		return list;
	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response addValoracion(@Context UriInfo uriInfo, Valoracion valoracion) {
		if (valoracion.getNombre() == null || "".equals(valoracion.getNombre())
				||valoracion.getPuntuacion() == null || "".equals(valoracion.getPuntuacion())
				||valoracion.getComentario() == null || "".equals(valoracion.getComentario()))
			throw new BadRequestException("Ni el nombre, ni la puntuacion, ni el comentario pueden ser nulos");

		repository.addValoracion(valoracion);

		// Builds the response. Returns the playlist the has just been added.
		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path(this.getClass(), "get");
		URI uri = ub.build(valoracion.getId());
		ResponseBuilder resp = Response.created(uri);
		resp.entity(valoracion);			
		return resp.build();
	}

	@PUT
	@Consumes("application/json")
	public Response updateValoracion(Valoracion valoracion) {
		Valoracion oldValoracion = repository.getValoracion(valoracion.getId());
		if (oldValoracion == null) {
			throw new NotFoundException("La valoracion con id = "+ valoracion.getId() +" no fue encontrada");			
		}

		// Update nombre
		if (valoracion.getNombre()!=null)
			oldValoracion.setNombre(valoracion.getNombre());
		// Update puntuacion
		if (valoracion.getPuntuacion()!=null)
			oldValoracion.setPuntuacion(valoracion.getPuntuacion());
		// Update comentario
		if (valoracion.getComentario()!=null)
			oldValoracion.setComentario(valoracion.getComentario());

		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id}")
	public Response removeValoracion(@PathParam("id") String vId) {
		Valoracion toberemoved = repository.getValoracion(vId);
		if (toberemoved == null)
			throw new NotFoundException("La valoracion con id = "+ vId +" no fue encontrada");
		else
			repository.deleteValoracion(vId);

		return Response.noContent().build();
	}

}
