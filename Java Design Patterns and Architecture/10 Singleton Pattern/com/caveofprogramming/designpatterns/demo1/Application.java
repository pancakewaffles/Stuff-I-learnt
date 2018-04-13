package com.caveofprogramming.designpatterns.demo1;

import javax.swing.SwingUtilities;

import com.caveofprogramming.designpatterns.demo1.controller.Controller;
import com.caveofprogramming.designpatterns.demo1.model.Model;
import com.caveofprogramming.designpatterns.demo1.view.View;

/*
 *  The Singleton Pattern is a design pattern where you have a class and you only allow one instance of that class to be created.
 *  That class can be accessed from anywhere in the code. A form of backdoor global variable.
 *  It enforces a single variable that all your other classes have to use.
 *  
 *  A Classic Example: A Database class. 
 *  
 *  
 */

public class Application {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){ 

			@Override
			public void run() {
				
				runApp();
			}
			
		});

	}
	
	public static void runApp(){
		Model model = new Model();
		
		View view = new View(model); 
		
		Controller controller = new Controller(model,view); 
		
		view.setLoginListener(controller); 
		
		
	}

}
