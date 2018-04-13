package com.caveofprogramming.designpatterns.demo1.model;

import java.sql.SQLException;
import java.util.List;

public interface PersonDAO {

	//SQL shenanigans for adding Persons
	void addPerson(Person person) throws SQLException;

	//More SQL shenanigans for getting Person by ID
	Person getPerson(int id) throws SQLException;

	//Even more SQL shenanigans for getting a whole group of Persons (People)
	List<Person> getPeople() throws SQLException;

	//Some more SQL shenanigans for updating Person
	int updatePerson(Person person) throws SQLException;

	//Some more SQL shenanigans for deleting Person by ID
	int deletePerson(int id) throws SQLException;

	//Hohoho - a master function for deleting all persons. You guessed it - more SQL shenanigans.
	int deleteAll() throws SQLException;

}