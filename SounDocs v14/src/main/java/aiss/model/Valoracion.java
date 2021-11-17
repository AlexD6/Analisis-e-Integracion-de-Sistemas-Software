package aiss.model;

public class Valoracion {
	
	private String id;
	private String nombre;
	private String puntuacion;
	private String comentario;
	
	public Valoracion() {
	}

	public Valoracion(String nombre, String puntuacion, String comentario) {
		this.nombre = nombre;
		this.puntuacion = puntuacion;
		this.comentario = comentario;
	}

	public Valoracion(String id, String nombre, String puntuacion, String comentario) {
		this.id = id;
		this.nombre = nombre;
		this.puntuacion = puntuacion;
		this.comentario = comentario;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(String puntuacion) {
		this.puntuacion = puntuacion;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	
}
