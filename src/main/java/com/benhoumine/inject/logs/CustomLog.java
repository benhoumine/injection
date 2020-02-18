package com.benhoumine.inject.logs;


import org.apache.log4j.Logger;

public class CustomLog {

	private Logger logger ; 
	
	public CustomLog(){
		logger = Logger.getLogger(CustomLog.class);
	}

	
	public void info(String message) {
		logger.info(message);
	}
	
	public void error(String message) {
		logger.error(message);
	}
	
}
