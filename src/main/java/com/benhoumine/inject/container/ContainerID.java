package com.benhoumine.inject.container;

import java.io.IOException;
import java.lang.reflect.Field;
import com.benhoumine.inject.annotations.Inject;
import com.benhoumine.inject.exceptions.AucuneImplementationException;
import com.benhoumine.inject.implementation.ImpContainerID;

/****
 *
 * 
 * @author Abdelkhalek BENHOUMINE cette classe qui se charge d'appliquer
 *         Injection
 *
 */
@SuppressWarnings({ "rawtypes" })
public class ContainerID {

	private String packageJava = "java";
	private ImpContainerID impContainerId;

	public ContainerID() {
		this.impContainerId = new ImpContainerID();
		this.packageJava = impContainerId.getPackageName();
		impContainerId.initialisationOnlyOneAnnotation(packageJava);
	}

	// Méthode pour l'injection des instances
	// Class.forName : pour instanciation dynamic
	// field.set : pour injecter la nouvelle instance avec l'instance récupérée
	public void injectClass(Object object) throws ClassNotFoundException, IOException, InstantiationException,
			IllegalAccessException, AucuneImplementationException {
		if (object != null) {
			Class<?> objectClass = object.getClass();
			for (Field field : objectClass.getDeclaredFields()) {
				field.setAccessible(true);
				if (field.isAnnotationPresent(Inject.class)) {
					String valueAnnotation = impContainerId.getValueInjectAnnotation(field);
					String interfaceDeclaration = field.getGenericType().toString().split(" ")[1];
					Class cls = Class.forName(impContainerId
							.getTypeAInstancier(impContainerId.getClassUsingValueAnnotation(valueAnnotation,
									impContainerId.getClassImplementInterface(packageJava, interfaceDeclaration))));
					Object obj = null;
					if (impContainerId.getOnlyOneInitialisation().getInstances().containsKey(cls.toString())) {
						obj = impContainerId.getOnlyOneInitialisation().getObject(cls.toString());
					} else {
						obj = cls.newInstance();
					}
					field.set(object, obj);
				}
			}
		}
	}

	// Retourner une instance d'une façon dynamique
	// Le type générique pour eviter l'utilisateur à faire le casting
	public <T> T newInstance(Class<T> classObject) throws InstantiationException, IllegalAccessException {
		return classObject.newInstance();

	}

}
