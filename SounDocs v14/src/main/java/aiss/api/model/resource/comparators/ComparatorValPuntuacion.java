package aiss.api.model.resource.comparators;

import java.util.Comparator;

import aiss.model.Valoracion;

public class ComparatorValPuntuacion implements Comparator<Valoracion>{
	
	public int compare(Valoracion v1, Valoracion v2) {
		return v1.getPuntuacion().compareTo(v2.getPuntuacion());
	}
}
