package com.caveofprogramming.designpatterns.demo1;

import javax.swing.SwingUtilities;

import com.caveofprogramming.designpatterns.demo1.controller.Controller;
import com.caveofprogramming.designpatterns.demo1.model.Model;
import com.caveofprogramming.designpatterns.demo1.view.View;

//The entry point

public class Application {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){ //We are using Swing. But this is pretty much all the Swing code we need.

			@Override
			public void run() {
				//Here we put in our main code
				runApp();
			}
			
		});

	}
	
	public static void runApp(){
		Model model = new Model();
		
		View view = new View(model); //The view takes the model because it represents data from the model
		
		Controller controller = new Controller(model,view); //The controller needs to tell the model AND the view what to do. It also listens to both, especially the view, in which it listens to user input. 
	
	}

}
