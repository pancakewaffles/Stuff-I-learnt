package com.caveofprogramming.designpatterns.demo1.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * This class basically functions as a Database. 
 * This is a Singleton Pattern.
 */
public class Database {
	
	private static Database instance = new Database();
	
	private Connection con;
	
	private Database(){ //so you can't "new Database()" from anywhere except in this class.
		
	}
	
	public static Database getInstance(){
		return instance;
	}
	
	//Using jdbc to connect to a mySQL database. You don't really have to know this.
	public void connect() throws Exception{
		if(con != null){
			return;
		}
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
		} catch (ClassNotFoundException e){
			throw new Exception("Driver not found.");
		}
		
		String url = String.format("url", 3306);
		
		con = DriverManager.getConnection(url,"squiffy","letmein");
		
		System.out.println("Connected to database.");
		
	}
	public void disconnect(){
		if(con != null){
			try{
				con.close();
				
			}catch (SQLException e){
				System.out.println("Can't close connection.");
			}
			
			System.out.println("Disconnected.");
		}
		
		
		
	}
}
