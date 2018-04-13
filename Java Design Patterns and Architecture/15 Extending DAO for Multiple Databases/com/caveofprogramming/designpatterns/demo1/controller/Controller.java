package com.caveofprogramming.designpatterns.demo1.controller;

import java.sql.SQLException;

import com.caveofprogramming.designpatterns.demo1.model.DAOFactory;
import com.caveofprogramming.designpatterns.demo1.model.Model;
import com.caveofprogramming.designpatterns.demo1.model.Person;
import com.caveofprogramming.designpatterns.demo1.model.PersonDAO;
import com.caveofprogramming.designpatterns.demo1.view.CreateUserEvent;
import com.caveofprogramming.designpatterns.demo1.view.CreateUserListener;
import com.caveofprogramming.designpatterns.demo1.view.View;

public class Controller implements CreateUserListener {
	private View view;
	private Model model;
	/*
	 We can't use what's below because we have abstracted the DAOFactory class.
	 
	//So instead of this:
	//private PersonDAO personDAO = new PersonDAO();
	
	//We can use this:
	//private PersonDAO personDAO = MySQLDAOFactory.getPersonDAO();
	
	*/
	
	// We can only use this: (Look at userCreated method)
	
	
	
	public Controller(View view, Model model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void userCreated(CreateUserEvent event) {
		/*
		 * What we did here is create a database-specific factory,
		 *                  to create a database-specific personDAO.
		 */
		DAOFactory factory = DAOFactory.getFactory(DAOFactory.MYSQL);
		
		PersonDAO personDAO = factory.getPersonDAO();
		
		
		System.out.println("Login event received: " + event.getName() + "; " + event.getPassword());
		
		try {
			personDAO.addPerson(new Person(event.getName(), event.getPassword()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
