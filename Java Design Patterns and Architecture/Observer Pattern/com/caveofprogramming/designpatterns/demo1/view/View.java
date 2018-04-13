package com.caveofprogramming.designpatterns.demo1.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.caveofprogramming.designpatterns.demo1.controller.Controller;
import com.caveofprogramming.designpatterns.demo1.model.Model;

/*
 * Here we have set out a Login Page.
 */

public class View extends JFrame implements ActionListener { 

	private Model model; 

	private JTextField nameField; 
	private JPasswordField passField;
	private JButton okButton;

	private LoginListener loginListener;
	
	public View(Model model) throws HeadlessException {
		super("MVC Demo"); 
		
		this.model = model;
		
		nameField = new JTextField(10);
		passField = new JPasswordField(10);
		okButton = new JButton("OK");
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		//form for JLabel Name
		gc.anchor = GridBagConstraints.LAST_LINE_END;
		gc.gridx = 1;
		gc.gridy = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(100,0,0,10);
		gc.fill = GridBagConstraints.NONE;
		
		add(new JLabel("Name: "),gc);
		
		//form for nameField
		gc.anchor = GridBagConstraints.LAST_LINE_START;
		gc.gridx = 2;
		gc.gridy = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(100,0,0,0);
		gc.fill = GridBagConstraints.NONE;
		
		add(nameField,gc);
		
		//so on, so forth
		gc.anchor = GridBagConstraints.LINE_END;
		gc.gridx = 1;
		gc.gridy = 2;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(0,0,0,10);
		gc.fill = GridBagConstraints.NONE;
		
		add(new JLabel("Password: "),gc);
		
		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 2;
		gc.gridy = 2;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(0,0,0,0);
		gc.fill = GridBagConstraints.NONE;
		
		add(passField,gc);
		
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 2;
		gc.gridy = 3;
		gc.weightx = 1;
		gc.weighty = 100;
		gc.fill = GridBagConstraints.NONE;
		
		add(okButton,gc);
		
		okButton.addActionListener(this);
		
		
		setSize(600,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String password = new String(passField.getPassword());
		String name = nameField.getText();
		
		System.out.println(name + ": " + password);
		
		//We could actually wrap the below in its own method for reusability's sake.
		if(loginListener != null){
			loginListener.loginPerformed(new LoginFormEvent(name,password)); //LoginFormEvent is the information class that we want to pass to the Controller.
		}
		//
		
	}

	public void setLoginListener(LoginListener loginListener) { // We want it to accept anything that implements the loginListener interface.
		this.loginListener = loginListener;
		
		//We are passing in an object that implements the LoginListener interface, and storing a reference to it.
		
		/*
		 * Let's recap a little because this is confusing.
		 * The Controller has implemented the LoginListener interface, with which we can use to access the Controller.
		 * The View, has a reference to the LoginListener interface, and we have a function setLoginListener to specify which Object with the LoginListener interface we want to refer to. In this case it is the Controller.
		 * We set the reference in the View, to the Controller.
		 * So now the View has a relationship with the Controller.
		 * 
		 * Once we have the relationship established, we can pass information from the View to the Controller.
		 * 
		 */
		
		/*
		 * So what if we do not want to use set, but rather add. i.e. We want to have multiple LoginListeners. In this case you can create an arrayList of LoginListeners, and then loop through the arrayList and call their specified method.
		 */
		
	}


}
