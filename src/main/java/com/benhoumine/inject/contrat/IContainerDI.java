package com.benhoumine.inject.contrat;

import java.io.File;

import java.io.IOException;
import java.util.List;
@SuppressWarnings("rawtypes")
public interface IContainerDI {
	
	public List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException ; 
	public Class[] getClasses(String packageName) throws ClassNotFoundException, IOException ; 
	public List<Class> getClassImplementInterface(String packageName , String interfaceName) throws ClassNotFoundException, IOException ;
	

}
