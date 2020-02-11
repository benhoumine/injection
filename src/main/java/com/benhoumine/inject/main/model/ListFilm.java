package com.benhoumine.inject.main.model;

import com.benhoumine.inject.annotations.Inject;


/***
 * Classe de Teste
 * @author Abdelkhalek Benhoumine
 *
 */
public class ListFilm {
	
	private String listName; 
	
	@Inject
	private Film film ; 
	
	public ListFilm(String listName) {
		this.listName = listName ; 
	}

	@Override
	public String toString() {
		return "ListFilm";
	}

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}
	

}
