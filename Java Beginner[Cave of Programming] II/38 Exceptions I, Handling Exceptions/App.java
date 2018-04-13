import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 * Handling Exceptions
 * Exceptions make code look ugly
 * Exceptions are error-handling mechanisms
 */
public class App {

	public App() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws FileNotFoundException { //Throw Declaration
		
		File file = new File("test.txt");
		
		FileReader fr = new FileReader(file); // FileNotFoundException; Exceptions are objects of the class Exception
		/*Two ways to deal with Exceptions
		 * Throw Declaration
		 * Try/Catch
		 */
		
		
		try { // Try/Catch Block : Try to do this code, if code throws an exception, go to catch block
			fr.close(); // IOException
			
			//This will not be executed if an exception is thrown.
			System.out.println("Continuing...");
			
		} catch (IOException e) {
			// Catch Block executed when there is an exception
			System.out.println("You can put whatever code you like here and handle the exception anyway you like!"); 
			e.printStackTrace();
			
			
		} 
		//Even if there is an exception caught by the catch block, this code below will still run
		System.out.println("Finished.");
		

	}

}
