package com.benhoumine.inject.contrat;


import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import com.benhoumine.inject.exceptions.AucuneImplementationException;
@SuppressWarnings("rawtypes")
public interface IContainerDI {
	
	public List<Class> getClassImplementInterface(String packageName , String interfaceName) throws ClassNotFoundException, IOException ;
	public Class getClassUsingValueAnnotation(String valueAnnotation, List<Class> classes)  throws AucuneImplementationException ;
	public String getValueInjectAnnotation(Field field) ;
	public String getTypeAInstancier(Class classes) throws AucuneImplementationException ; 
	public String getPackageName() ; 
}
