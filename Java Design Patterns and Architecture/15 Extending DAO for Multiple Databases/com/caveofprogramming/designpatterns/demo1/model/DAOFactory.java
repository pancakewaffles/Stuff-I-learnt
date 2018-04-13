package com.caveofprogramming.designpatterns.demo1.model;

public abstract class DAOFactory {
	
	public static final int MYSQL = 0;
	public static final int ORACLE = 1;

	public abstract PersonDAO getPersonDAO(); //Is a method. Don't worry.

	public abstract LogDAO getLogDAO(); //Is a method. Don't worry.
	
	public static DAOFactory getFactory(int type){ //This method will return the DAOFactory of the right type.
		switch(type){
		case MYSQL:
			return new MySQLDAOFactory();
		case ORACLE:
			return new OracleDAOFactory();
		default:
			return null;
		}
	}

}