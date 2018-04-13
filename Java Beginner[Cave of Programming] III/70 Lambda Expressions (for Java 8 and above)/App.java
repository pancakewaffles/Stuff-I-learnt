/*
 * Lambda Expressions are just a way of passing a block of code to a method.
 * Purely a new way of doing methods in anonymous classes.
 * E.g. Pass some code to a thread.
 *      Pass some code to a Swing button.
 */
interface Executable{ //A function interface which only has one method in the interface
	int execute(int a, int b);
}

interface StringExecutable{
	int execute(String a);
}
class Runner{
	public void run(Executable e){
		//So we are going to pass a block of code to run().
		//To do this in previous Javas we need to create an interface.
		//This is tedious.
		System.out.println("Executing code block ...");
		int value = e.execute(12,14);
		System.out.println("Return value is: "+value);
	}
	
	public void run(StringExecutable e){
		System.out.println("Executing code block ...");
		int value = e.execute("Hello");
		System.out.println("Return value is: "+value);
	}
}
public class App {
	/*
	 Some valid examples of Lambda Expressions:
	 
	 () -> {
		System.out.println("Hello there from Lambda Expression.");
		System.out.println("More lines of code in a Lambda expression.");
		return 8;
			}
			
	
	 () -> {
		return 8;
			}
			
	
	 () -> 8;
	 
	 
	 (int a) -> 8; //Does not mean that a = 8.
	 
	 
	 (int a) -> {
		System.out.println("Hello there from Lambda Expression.");
		System.out.println("More lines of code in a Lambda expression.");
		return 8;
			}
	
	  (a) -> {  //Java can infer the type of parameters, so you don't have to specify them. But I prefer specifying them.
		System.out.println("Hello there from Lambda Expression.");
		System.out.println("More lines of code in a Lambda expression.");
		return 7+a;
			}
			
		
	 a -> {  //Also valid.
		System.out.println("Hello there from Lambda Expression.");
		System.out.println("More lines of code in a Lambda expression.");
		return 7+a;
			}
			
			
	(int a, int b) -> {
		System.out.println("Hello there from Lambda Expression.");
		System.out.println("More lines of code in a Lambda expression.");
		return 7+a;
	}
	 
	*/


	public static void main(String[] args) {
		
		int c = 100; // (effectively) final
		//Mustn't do this: c = 8;
		
		int d = 77;
		
		Runner runner = new Runner();
		
		//Old way of doing : Anonymous classes
		runner.run(new Executable(){   

			@Override
			public int execute(int a, int b) {
				System.out.println("Hello there.");
				// Can do this in methods of anonymous classes because they have their own scopes int d = 8;
				return a + b + c;
				
			}
			
		}); 
		
		System.out.println("===================");
		
		////////// Using the Lambda Expression /////////////
		// Syntax : () -> { code you want to run }
		//You still need the interface though. What it does is it replace a method in the interface.

		runner.run((int a, int b) -> {
			System.out.println("Hello there from Lambda Expression.");
			System.out.println("More lines of code in a Lambda expression.");
			//Cannot do this because this scope is the main() method. int d = 99;
			return a + b + c;
		});
		
		System.out.println("===================");
		
		//Functional Programming
		
		Executable ex = (int a, int b) -> {
			System.out.println("Hello there from Lambda Expression.");
			System.out.println("More lines of code in a Lambda expression.");
			return a + b + c;
		};
		
		runner.run(ex);
		
		Object codeblock = (Executable)(int a, int b) -> { //Casting Object to Executable
			System.out.println("Hello there from Lambda Expression.");
			System.out.println("More lines of code in a Lambda expression.");
			return a + b + c;
		};
		

	}

}
