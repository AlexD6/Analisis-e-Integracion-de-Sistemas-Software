package aiss.model.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import aiss.model.Valoracion;

public class MapValoracionRepository implements ValoracionRepository{
	Map<String, Valoracion> valoracionMap;
	private static MapValoracionRepository instance = null;
	private int index = 0;
	
	public static MapValoracionRepository getInstance() {
		if(instance == null) {
			instance = new MapValoracionRepository();
			instance.init();
		}
		return instance;
	}
	
	public void init() {
		valoracionMap = new HashMap<String, Valoracion>();
		
		Valoracion valoracion1 = new Valoracion();
		valoracion1.setNombre("Roberto");
		valoracion1.setPuntuacion("7");
		valoracion1.setComentario("Me parece muy util");
		addValoracion(valoracion1);
		
		Valoracion valoracion2 = new Valoracion();
		valoracion2.setNombre("Miguel");
		valoracion2.setPuntuacion("9");
		valoracion2.setComentario("Me encanta");
		addValoracion(valoracion2);
		
		Valoracion valoracion3 = new Valoracion();
		valoracion3.setNombre("Luis");
		valoracion3.setPuntuacion("4");
		valoracion3.setComentario("Un poco floja");
		addValoracion(valoracion3);
		
		Valoracion valoracion4 = new Valoracion();
		valoracion4.setNombre("Pedro");
		valoracion4.setPuntuacion("6");
		valoracion4.setComentario("Normalita");
		addValoracion(valoracion4);
		
		Valoracion valoracion5 = new Valoracion();
		valoracion5.setNombre("Jacinto");
		valoracion5.setPuntuacion("8");
		valoracion5.setComentario("Interesante");
		addValoracion(valoracion5);
	}
	
	public void addValoracion(Valoracion v) {
		String id = "v" + index++;
		v.setId(id);
		valoracionMap.put(id, v);
	}
	
	public Collection<Valoracion> getAllValoraciones() {
		return valoracionMap.values();
	}

	public Valoracion getValoracion(String vId) {
		return valoracionMap.get(vId);
	}

	public void updateValoracion(Valoracion v) {
		Valoracion valoracion = valoracionMap.get(v.getId());
		valoracion.setNombre(v.getNombre());
		valoracion.setPuntuacion(v.getPuntuacion());
		valoracion.setComentario(v.getComentario());
	}

	public void deleteValoracion(String vId) {
		valoracionMap.remove(vId);
	}
}
