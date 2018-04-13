/*
 * This is a tutorial on recursion in Java
 * Recursion is a basic thing that all programmers should learn
 */
public class App {

	public static void main(String[] args) {
		
		long value = 4;
		//E.g. 4! = 4*3*2*1 (factorial 4)
		System.out.println(factorial(5));
		
	}
	
	private static long factorial(long value){ //Remember, if you want to use methods in the App class from the main method, 
											  //you have to declare it as static
										      //because you did not instantiate the class App
		System.out.println(value);
		
		/*
		 * Stack Memory vs Heap Memory
		 * Stack Memory: a relatively small area of memory that Java uses that remembers function calls and local variables
		 * Heap Memory: area of memory that is used to store the new variables (i.e. called with new Machine() etc
		 */
		
		if(value ==1){ //Stopping the recursion : Must always be placed before the method calls itself again
			return 1;
		}
		return factorial(value - 1)*value; //Recursion: Calling the same method from itself
		
		//Look at Tower of Hanoi puzzle for another implementation of the recursion method
		 
		
		
	}

}
