package com.benhoumine.inject.implementation;

import java.util.HashMap;

/***
 * 
 * Class Qui contient les Objects qui doivent être instancié une seule fois
 * 
 * @author abdelkhalek BENHOUMINE
 *
 */
public class ImpOnlyOne {

	private static ImpOnlyOne implementOne;
	private HashMap<String, Object> onlyOnes;

	private ImpOnlyOne() {
		// Pour eviter l'instaciation
		onlyOnes = new HashMap<>();
	}

	public static ImpOnlyOne getInstanceOnlyOne() {
		if (implementOne == null) {
			implementOne = new ImpOnlyOne();
		}
		return implementOne;
	}

	public HashMap getOnlyOnes() {
		return onlyOnes;
	}

	public void insertObject(String key, Object object) {
		onlyOnes.put(key, object);
	}

	public Object getObject(String key) {
		return onlyOnes.get(key);
	}
}
