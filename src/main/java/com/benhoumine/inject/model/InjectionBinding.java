package com.benhoumine.inject.model;

public class InjectionBinding {
	
	private String from ; 
	private String to ; 
	
	public InjectionBinding() {
		this.from = "";
		this.to="";
		
	}
	
	public void to(String to) {
		this.to = to ; 
	}
	
	public void from(String from) {
		this.from = from ; 
	}

	public String to() {
		return this.to ; 
	}
	
	public String from() {
		return this.from ; 
	}
	
	
}
