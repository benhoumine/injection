package com.benhoumine.inject.implementation;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import com.benhoumine.inject.contrat.IContainerDI;

@SuppressWarnings("rawtypes")
public class ImpContainerID implements IContainerDI {

	
	//Cette méthode  est pour trouver toutes les classes qui existe dans un package
	@Override
	public List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
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
		            classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
		        }
		    }
		    return classes;
	}

	//Cette méthode est pour trouver toutes les classes qui existe dans un package
	@Override
	public Class[] getClasses(String packageName) throws ClassNotFoundException, IOException {
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
	

	//Cette méthode a pour objectif de determiner les classes qui implemente un interface
	@Override
	public List<Class> getClassImplementInterface(String packageName, String interfaceName)
			throws ClassNotFoundException, IOException {
		Class[] classes = getClasses(packageName);
		List<Class> classImplementInterface = new ArrayList<>() ; 
		for(Class objectClass : classes) {
			for(Class interfaceClass : objectClass.getInterfaces()) {
					if(interfaceClass.getTypeName().contains(interfaceName)) {
						classImplementInterface.add(objectClass);
					}
			}
		}
		return classImplementInterface ; 
	}
}
