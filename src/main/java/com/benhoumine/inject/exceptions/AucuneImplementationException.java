package com.benhoumine.inject.exceptions;


/***
 * 
 * 
 * Cette Exception se déclenche lorsqu'il n'y a aucune implémentation pour une interface
 *
 *
 */
public class AucuneImplementationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AucuneImplementationException(String msg) {
		super(msg);
	}
	
}
