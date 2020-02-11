package com.benhoumine.inject.main.model;

import com.benhoumine.inject.annotations.Inject;

public class BudgetModule {

	  @Inject("BMCI")
	  private Bank bank;
	  
	  @Inject
	  private Employe interneBMCI ; 

	  public BudgetModule() {

	  }

	  public String toString() {
	    return "Budget module with bank " + this.bank.toString() ; 
	  }

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public Employe getInterneBMCI() {
		return interneBMCI;
	}

	public void setInterneBMCI(Employe interneBMCI) {
		this.interneBMCI = interneBMCI;
	}
	 	  
	  
	}
