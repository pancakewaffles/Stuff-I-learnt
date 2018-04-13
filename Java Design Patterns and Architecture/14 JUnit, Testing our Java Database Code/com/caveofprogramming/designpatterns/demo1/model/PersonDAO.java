package com.caveofprogramming.designpatterns.demo1.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 * One DAO class per table or view
 * CRUD - Create, retrieve, update, delete
 * 
 * So basically every object that you want to put into the database will require a DAO for it.
 * The DAO interacts with the database to add/remove the object it represents.
 */


public class PersonDAO {
	
	//SQL shenanigans for adding Persons
	public void addPerson(Person person) throws SQLException {
		
		Connection conn = Database.getInstance().getConnection();
		
		PreparedStatement p = conn.prepareStatement("insert into people (name, password) values (?,?)");
		
		p.setString(1, person.getName());
		p.setString(2, person.getPassword());
		
		p.executeUpdate();
		
		p.close();
		
	}
	//More SQL shenanigans for getting Person by ID
	public Person getPerson(int id) throws SQLException {
		Connection conn = Database.getInstance().getConnection();

		String sql = "select id, name, password from people where id=? order by id";
		PreparedStatement selectStatement = conn.prepareStatement(sql);
		
		selectStatement.setInt(1, id);

		ResultSet results = selectStatement.executeQuery();

		Person person = null;

		if (results.next()) {
			String name = results.getString("name");
			String password = results.getString("password");

			person = new Person(id, name, password);
		}

		results.close();
		selectStatement.close();

		return person;
	}
	//Even more SQL shenanigans for getting a whole group of Persons (People)
	public List<Person> getPeople() throws SQLException {
		
		List<Person> people = new ArrayList<Person>();
		
		Connection conn = Database.getInstance().getConnection();
		
		String sql = "select id, name, password from people order by id";
		Statement selectStatement = conn.createStatement();
		
		ResultSet results = selectStatement.executeQuery(sql);
		
		while(results.next()) {
			int id = results.getInt("id");
			String name = results.getString("name");
			String password = results.getString("password");
			
			Person person = new Person(id, name, password);
			people.add(person);
		}
		
		results.close();
		selectStatement.close();
		
		return people;
	}
	//Some more SQL shenanigans for updating Person
	public int updatePerson(Person person) throws SQLException {
		Connection conn = Database.getInstance().getConnection();
		
		PreparedStatement p = conn.prepareStatement("update people set name=?, password=? where id=?");
		p.setString(1, person.getName());
		p.setString(2, person.getPassword());
		p.setInt(3,person.getId());
		
		int updated = p.executeUpdate();
		
		p.close();
		
		return updated;
		
	}
	//Some more SQL shenanigans for deleting Person by ID
	public int deletePerson(int id) throws SQLException {
		Connection conn = Database.getInstance().getConnection();

		PreparedStatement p = conn.prepareStatement("delete from people where id=?");

		p.setInt(1, id);

		int deleted = p.executeUpdate();

		p.close();
		
		return deleted;
	}
	//Hohoho - a master function for deleting all persons. You guessed it - more SQL shenanigans.
	public int deleteAll() throws SQLException {
		Connection conn = Database.getInstance().getConnection();

		PreparedStatement p = conn
				.prepareStatement("delete from people");

		int deleted = p.executeUpdate();

		p.close();
		
		return deleted;
	}
}
