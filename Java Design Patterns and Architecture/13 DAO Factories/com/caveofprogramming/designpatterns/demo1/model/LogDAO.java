package com.caveofprogramming.designpatterns.demo1.model;

import java.util.List;

public class LogDAO {
	
	public void addEntry(String message){
		// Get current time and add log message to database.
		
	}
	
	public List<Log> getEntries(int number){
		//Get latest "number" log messages.
		return null;
		
	}
	
	//Maybe no need for update or delete in this case. It's just setting and getting from the database.
}
