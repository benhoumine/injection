package com.benhoumine.inject.main.model;

import com.benhoumine.inject.annotations.Inject;

public class TestOnlyOne {

	@Inject("BMCI")
	private Bank bank;

	@Inject
	private Employe interneBMCI;

	public TestOnlyOne() {

	}

	public Bank getBank() {
		return bank;
	}

}
