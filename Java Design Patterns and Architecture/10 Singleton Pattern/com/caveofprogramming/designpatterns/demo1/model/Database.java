package com.caveofprogramming.designpatterns.demo1.model;

/*
 * This class basically functions as a Database. 
 * This is a Singleton Pattern.
 */
public class Database {
	//--------------New way of implementing the Singleton Pattern---------------
	private static Database instance = new Database();
	
	private Database(){ //so you can't "new Database()" from anywhere except in this class.
		
	}
	
	public static Database getInstance(){
		return instance;
	}
	
	//---------------Old way of implementing the Singleton Pattern--------------
	/* Later instantiation because you create the database only at the last possible moment, when someone calls instanceOld.
	 * Saves memory, speed up things. 
	 * But not thread-safe, because the first thread can call instanceOld, and a second thread can call instanceOld.
	 * So the both creates instanceOld because the second thread calls instanceOld before the first thread can create instanceOld.
	 * 
	 */
	private static Database instanceOld;
	public static Database getInstanceOld(){
		if(instanceOld == null){
			instanceOld = new Database();
		}
		
		return instanceOld;
	}
	
	public void connect(){
		System.out.println("Connected to database.");
	}
	public void disconnect(){
		System.out.println("Disconnected.");
	}
}
