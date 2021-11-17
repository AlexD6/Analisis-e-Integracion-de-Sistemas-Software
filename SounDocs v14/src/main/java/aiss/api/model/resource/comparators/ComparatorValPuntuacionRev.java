package aiss.api.model.resource.comparators;

import java.util.Comparator;

import aiss.model.Valoracion;

public class ComparatorValPuntuacionRev implements Comparator<Valoracion>{
	public int compare(Valoracion v1, Valoracion v2) {
		return v2.getPuntuacion().compareTo(v1.getPuntuacion());
}
}