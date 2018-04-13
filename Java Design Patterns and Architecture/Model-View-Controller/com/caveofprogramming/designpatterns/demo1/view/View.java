package com.caveofprogramming.designpatterns.demo1.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.caveofprogramming.designpatterns.demo1.model.Model;

/*
 * The view represents the data from the model
 */
public class View extends JFrame implements ActionListener{ //JFrame creates a window

	private Model model; //The view has a handler for the model
	
	private JButton helloButton; //Observer Pattern Theory in practice here. The Button is the Subject.
	
	private JButton goodbyeButton;
	
	public View(Model model) throws HeadlessException {
	super("MVC Demo"); //Supplies the title for the frame
	
	this.model = model;
	
	helloButton = new JButton("Hello!");
	goodbyeButton = new JButton("Goodbye!");
	
	// Swing. If you don't get it now, don't worry.
	setLayout(new GridBagLayout()); //Tell the window which layout manager to use.
	
	//Then you fill out a form that details the properties of the window.
	GridBagConstraints gc = new GridBagConstraints();
	gc.anchor = GridBagConstraints.CENTER;
	gc.gridx = 1;
	gc.gridy = 1;
	gc.weightx = 1;
	gc.weighty = 1;
	gc.fill = GridBagConstraints.NONE;
	
	//Then we add things to the window with the form.
	add(helloButton,gc);
	
	helloButton.addActionListener(this); //"add" because you can have multiple listeners. "Set" would imply a fixed number of listeners.
	                                     // ActionListener is an interface.  
	/*Okay this is important. We can pass a reference of View to this Subject because we made the class of View implement the ActionListener interface.
	  We are able to pass the reference of the View object to the Subject because we have created a View object ( new View() ) in our main Application class.
	  
	  When helloButton is acted upon (e.g. clicked), it immediately notifies the Listener, which we have designated the View object to. i.e. it calls the method actionPerformed(ActionEvent e).
	  
	*/
	
	//If you want to create another button you have to create another form for it. This form is for goodbyeButton.
	gc.anchor = GridBagConstraints.CENTER;
	gc.gridx = 1;
	gc.gridy = 2;
	gc.weightx = 1;
	gc.weighty = 1;
	gc.fill = GridBagConstraints.NONE;
	
	add(goodbyeButton,gc);
	
	goodbyeButton.addActionListener(this);
	
	goodbyeButton.addActionListener(new ActionListener(){ //Anonymous class as an ActionListener. ActionListener is NOT an object, it is an interface.
		                                                  //Also note that if we click goodbyeButton, both Listeners will be activated.

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("Sorry to see you go.");
			
		}  
		
	}); 
	
	// Swing. These three lines create the window. Important three lines.
	setSize(600,500);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setVisible(true);
}


	@Override //Remember, the View class has implemented the ActionListener interface.
	public void actionPerformed(ActionEvent e) {
		
		/*ActionEvent e is interesting. It remembers which button it was clicked and activated from.
		  It is part of the observer pattern.
		*/
		JButton source = (JButton)e.getSource();
		
		if(source == helloButton){
			System.out.println("Hello there!");
		}
		else{
			System.out.println("Some other button.");
		}
		
		
	}
	
	
	

}
