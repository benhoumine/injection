package com.benhoumine.inject.implementation;

import java.util.HashMap;

/***
 * 
 * Class Qui contient les Objects qui doivent être instancié une seule fois
 * 
 * @author abdelkhalek BENHOUMINE
 *
 */
public class ImpOneInstance {

	private static ImpOneInstance impOneInstance;
	private HashMap<String, Object> instances;

	private ImpOneInstance() {
		// Pour eviter l'instaciation
		instances = new HashMap<>();
	}

	public static ImpOneInstance getInstanceOnlyOne() {
		if (impOneInstance == null) {
			impOneInstance = new ImpOneInstance();
		}
		return impOneInstance;
	}

	public HashMap getInstances() {
		return instances;
	}

	public void insertObject(String key, Object object) {
		instances.put(key, object);
	}

	public Object getObject(String key) {
		return instances.get(key);
	}
}
