package com.benhoumine.inject.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
 * 
 * cette annotation est pour spécifier à l'utilisateur la classe par defaut
 * @author abdelkhalek BENHOUMINE
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Default {

}
