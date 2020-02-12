package com.benhoumine.inject.main.model;

public class EmployeBMCIBANK implements Employe {

	private static int compteur = 0;


	public EmployeBMCIBANK() {
		compteur++;
	}

	public int getCompter() {
		return compteur;
	}

}
