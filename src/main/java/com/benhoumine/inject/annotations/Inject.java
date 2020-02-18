package com.benhoumine.inject.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/****
 * 
 * 
 * 
 * @author abdelkhalek BENHOUMINE
 * Annotation pour injecter un objet
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Inject {
	
    public String value() default "";
    
}
