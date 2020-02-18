package com.benhoumine.inject.main.model;

import com.benhoumine.inject.annotations.OneInstance;

@OneInstance
public class BMCI implements Bank {

	private static int compteur = 0;

	public BMCI() {
		compteur++;
	}

	public int getCompter() {
		return compteur;
	}
}
