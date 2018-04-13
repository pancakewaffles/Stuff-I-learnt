/*
 * Passing by Value vs Passing by Reference
 * Java only has Passing by Value. We always pass by value.
 * So what's passing by reference?
 * In C++ we have person& -> The & at the end tells you that the variable that you pass, is the actual variable, and not a copy. Changing that changes the actual value.
 */
public class App {

	public static void main(String[] args) {

		App app = new App(); //So I can use non-static methods in the class App
		
		
		//===========Passing Primitive Types===================
		int value = 7;    //Really allocating memory to store int of 7
		System.out.println("1. Value is: "+value);
		
		app.show(value);
		
		System.out.println("4. Value is: "+value);
		
		//===========Passing Non-Primitive Types================
		
		System.out.println(); //Blank line
		Person person = new Person("Bob"); //allocating enough memory to hold the address of that object; person stores an address
										   //Address of a house, rather than the actual house
		System.out.println("1. Person is: "+person);
		
		app.show(person);
		
		System.out.println("4. Person is: "+person);
		//======================================================
	}
	
	public void show(int value){ //Passing by Value. Basically making a copy of value (the outer one), calling it value (the inner one for show()), and passing it into show()
		
		//The (int value) can only be used within the scope of show()
		
		System.out.println("2. Value is: "+value);
		
		value = 8; //Editing the copy, not the original value in the main method
		
		System.out.println("3. Value is: "+value);
	}
	
	public void show(Person person){ //person is a copy of the address, not the actual house
		System.out.println("2. Person is: "+person);
		
		person.setName("Sue"); //person at this moment refers to Person("Bob"), and calling its method will change it accordingly
		
		person = new Person("Mike"); //Changing the copy of the address, not the address itself, not the actual house itself
		
		System.out.println("3. Person is: "+person);
		
	}

}
