package com.benhoumine.inject.implementation;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import com.benhoumine.inject.annotations.Inject;
import com.benhoumine.inject.contrat.IContainerDI;
import com.benhoumine.inject.exceptions.AucuneImplementationException;
import com.benhoumine.inject.logs.CustomLog;

@SuppressWarnings("rawtypes")
public class ImpContainerID implements IContainerDI {

	private ImpOnlyOne onlyOneInitialisation;
	private static final CustomLog LOGGER = new CustomLog();
	

	public ImpContainerID() {
		LOGGER.info("(===>  INITIALISATION PLUGIN INJECTION ('-') Abdelkhalek BENHOUMINE <===)");
		onlyOneInitialisation = ImpOnlyOne.getInstanceOnlyOne();
	}

	// Cette méthode est pour trouver toutes les classes qui existe dans un package
	private List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
		List<Class> classes = new ArrayList<>();
		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file, packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				classes.add(
						Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
			}
		}
		return classes;
	}

	// Cette méthode est pour trouver toutes les classes qui existe dans un package
	private Class[] getClasses(String packageName) throws ClassNotFoundException, IOException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		assert classLoader != null;
		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = classLoader.getResources(path);
		List<File> dirs = new ArrayList<>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}
		ArrayList<Class> classes = new ArrayList<>();
		for (File directory : dirs) {
			classes.addAll(findClasses(directory, packageName));
		}
		return classes.toArray(new Class[classes.size()]);
	}

	// Cette méthode a pour objectif de determiner les classes qui implemente un
	// interface
	@Override
	public List<Class> getClassImplementInterface(String packageName, String interfaceName)
			throws ClassNotFoundException, IOException {
		Class[] classes = getClasses(packageName);
		List<Class> classImplementInterface = new ArrayList<>();
		for (Class objectClass : classes) {
			for (Class interfaceClass : objectClass.getInterfaces()) {
				if (interfaceClass.getTypeName().contains(interfaceName)) {
					classImplementInterface.add(objectClass);
				}
			}
		}
		return classImplementInterface;
	}

	@Override
	public Class getClassUsingValueAnnotation(String valueAnnotation, List<Class> classes)
			throws AucuneImplementationException {

		if (classes.isEmpty())
			throw new AucuneImplementationException(
					"Il n'existe aucune classe qui utilise l'annotation " + valueAnnotation);
		for (Class clazz : classes) {
			if (clazz.toString().contains(valueAnnotation)) {
				return clazz;
			}
		}
		return classes.get(0);
	}

	public void initialisationOnlyOneAnnotation(String packageName) {
		try {
			Class[] classes = getClasses(packageName);
			for (Class clazz : classes) {
				for (Annotation annotation : clazz.getDeclaredAnnotations()) {
					if (annotation.toString().contains("OnlyOne")) {
						onlyOneInitialisation.insertObject(clazz.toString(), clazz.newInstance());
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	// Récupération de la valeur de l'annotation
	@Override
	public String getValueInjectAnnotation(Field field) {
		String annotationValue = field.getAnnotation(Inject.class).value();
		if (annotationValue.isEmpty()) {
			return field.getName();
		} else {
			return annotationValue;
		}
	}

	@Override
	public String getTypeAInstancier(Class classes) throws AucuneImplementationException {
		String[] classesSplit = classes.toString().split(" ");
		if (classesSplit == null || classesSplit.length==0)
			throw new AucuneImplementationException("Aucune implémentation");
		return classesSplit[1];
	}

	@Override
	// methode pour récuperer le package de la classe, pour injecter l'object d'une
	// maniere dynamic
	public String getPackageName() {
		String packageName = this.getClass().getPackage().getName();
		if (packageName.contains(".")) {
			return packageName.split("\\.")[0];
		}
		return packageName;
	}

	public ImpOnlyOne getOnlyOneInitialisation() {
		return onlyOneInitialisation;
	}
}
