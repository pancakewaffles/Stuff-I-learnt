package com.caveofprogramming.designpatterns.demo1.model;

/*
 * I removed the static label from the methods because I am calling them from the object. 
 */

public class MySQLDAOFactory extends DAOFactory {
	/* (non-Javadoc)
	 * @see com.caveofprogramming.designpatterns.demo1.model.DAOFactory#getPersonDAO()
	 */
	@Override
	public PersonDAO getPersonDAO(){ 
		return new MySQLPersonDAO();
	}
	
	/* (non-Javadoc)
	 * @see com.caveofprogramming.designpatterns.demo1.model.DAOFactory#getLogDAO()
	 */
	@Override
	public LogDAO getLogDAO(){
		return new MySQLLogDAO();
	}
}
