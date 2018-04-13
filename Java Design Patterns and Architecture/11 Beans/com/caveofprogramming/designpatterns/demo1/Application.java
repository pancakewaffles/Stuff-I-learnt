package com.caveofprogramming.designpatterns.demo1;

import javax.swing.SwingUtilities;

import com.caveofprogramming.designpatterns.demo1.controller.Controller;
import com.caveofprogramming.designpatterns.demo1.model.Model;
import com.caveofprogramming.designpatterns.demo1.view.View;

/*
 *  A Bean is an transfer object, an information packet that I will send to my database.
 *  It is used in the Database Access Object (DAO) pattern.
 *  
 *  Our Bean is the Person class that we created in the Model package.
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
