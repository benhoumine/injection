package com.benhoumine.inject.container;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import com.benhoumine.inject.annotations.Inject;
import com.benhoumine.inject.exceptions.AucuneImplementationException;
import com.benhoumine.inject.implementation.ImpContainerID;

/****
 *
 * 
 * @author Abdelkhalek BENHOUMINE
 * cette classe qui se charge d'appliquer Injection
 *
 */
@SuppressWarnings("rawtypes")
public class ContainerID {
	
	private String packageJava = "java";
	private ImpContainerID impContainerId;
	

	public ContainerID() {
		//pour respecter les normes Java
		this.packageJava = getPackageName();
		this.impContainerId = new ImpContainerID() ; 
	}
	
	public ContainerID(String packageJava) {
		this.packageJava = packageJava;
		this.impContainerId = new ImpContainerID() ; 
	}
	
	
	//Méthode pour l'injection des instances
	//Class.forName : pour instanciation dynamic 
	public void injectClass(Object object) throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException, AucuneImplementationException {	
		if(object != null) {
				Class<?> objectClass = object.getClass();
				for (Field field : objectClass.getDeclaredFields()) {
					field.setAccessible(true);
					if (field.isAnnotationPresent(Inject.class)) {
					   String valueAnnotation = getValueInjectAnnotation(field);
					   String interfaceDeclaration =  field.getGenericType().toString().split(" ")[1] ; 
					   Class cls = Class.forName(getTypeAInstancier(getClassUsingValueAnnotation(valueAnnotation,impContainerId.getClassImplementInterface(packageJava,interfaceDeclaration))));
					   Object obj = cls.newInstance();
					   field.set(object, obj);
					}
																	}	
							}
	}
	
	//Retourner une instance d'une façon dynamique
	public Object newInstance(Class classObject) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class cls = Class.forName(classObject.toString().split(" ")[1]);
		return cls.newInstance();
		
	}
	
	private String getTypeAInstancier(Class classes) throws AucuneImplementationException {
		String[] classesSplit = classes.toString().split(" ");
		if(classesSplit == null) throw new AucuneImplementationException("Aucune implémentation");
		return classesSplit[1];
	}	
	
	private Class getClassUsingValueAnnotation(String valueAnnotation, List<Class> classes)  throws AucuneImplementationException {
		
		if(classes.isEmpty()) throw new AucuneImplementationException("Il n'existe aucune classe qui utilise l'annotation "+valueAnnotation);
		for(Class clazz :  classes) {
			if(clazz.toString().contains(valueAnnotation)) {
				return clazz; 
			}
		}
		return classes.get(0); 
	}
	
	private String getValueInjectAnnotation(Field field) {
		String annotationValue = field.getAnnotation(Inject.class).value();
		if (annotationValue.isEmpty()) {
			return field.getName();
		} else {
			return annotationValue;
		}
	}
	
	//methode pour récuperer le package de la classe, pour injecter l'object d'une maniere dynamic
	private String getPackageName() {
		String packageName = this.getClass().getPackage().getName() ; 
		if(packageName.contains(".")) {
			return packageName.split("\\.")[0]; 
		}
		return packageName ; 
	}

}
