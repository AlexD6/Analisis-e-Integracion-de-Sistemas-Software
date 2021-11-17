package aiss.model.repository;

import java.util.Collection;

import aiss.model.Valoracion;

public interface ValoracionRepository {

	public void addValoracion(Valoracion v);
	public Collection<Valoracion> getAllValoraciones();
	public Valoracion getValoracion(String vId);
	public void updateValoracion(Valoracion v);
	public void deleteValoracion(String vId);
}
