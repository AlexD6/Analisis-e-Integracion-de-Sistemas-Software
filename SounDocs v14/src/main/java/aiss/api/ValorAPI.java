package aiss.api;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import aiss.api.resources.ValoracionResource;

public class ValorAPI extends Application {
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> classes = new HashSet<Class<?>>();
	
	public ValorAPI() {
		singletons.add(ValoracionResource.getInstance());
	}

	public Set<Class<?>> getClasses() {
		return classes;
	}
	
	public Set<Object> getSingletons() {
		return singletons;
	}

}
