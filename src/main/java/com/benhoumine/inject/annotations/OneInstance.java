package com.benhoumine.inject.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
 * 
 * Cette Annotation est pour instancie une seul instance (Singleton)
 * 
 * @author abdelkhalek BENHOUMINE
 * 
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface OneInstance {

}
