package com.caveofprogramming.designpatterns.demo1.model;

import java.util.List;

public interface LogDAO {

	void addEntry(String message);

	List<Log> getEntries(int number);

}