package com.caveofprogramming.designpatterns.demo1;

import javax.swing.SwingUtilities;

import com.caveofprogramming.designpatterns.demo1.controller.Controller;
import com.caveofprogramming.designpatterns.demo1.model.Model;
import com.caveofprogramming.designpatterns.demo1.view.View;

/*
 * In our last lesson we dealt with the Observer Pattern in the context of a MVC using a pre-determined JButton, ActionListener etc. i.e. the Observer Pattern on a micro-scale.
 * Now, we shall implement our own Observer Pattern in the context of a MVC. i.e. The Controller listens to the View and the Model, and we have to implement those relationships. i.e. the Observer Pattern on a macro-scale.
 * So, the Controller will be the Listener/Observer, while the View will be the Subject.
 * 
 * We shall create this in the context of a Login Page.
 * When the view (with its ActionListener) picks up on the fact that a user has tried to login, it will notify the Controller to do something.
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
		
		view.setLoginListener(controller); //Our custom Listener. We are setting the Controller as a Listener for the Login event in View.
	    //This is important. What we want to do is NOT to pass the whole Controller object into the Listener, we want to abstract the Controller behind an interface.                      
	    //So we want to pass in an object that implements an interface with methods to access the Controller.
		
		
	}

}
