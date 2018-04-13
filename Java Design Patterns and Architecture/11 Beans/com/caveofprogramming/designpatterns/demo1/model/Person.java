package com.caveofprogramming.designpatterns.demo1.model;

/*
 * This is a Bean. A lightweight little class that only has get/set methods.
 */
public class Person {
	private int id;
	private String name;
	private String password;
	
	public Person(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
