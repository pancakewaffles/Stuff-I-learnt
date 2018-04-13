import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

/*
 * Multiple Exceptions: Oh God.
 */
public class App {

	public App() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Test test = new Test();
		
		/*Try with Multiple Catch
		try {
			test.run(); //Cuz test.run() threw exceptions
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (ParseException e) { 
			
			e.printStackTrace();
		} 
		*/
		
		/* Try-Multi-Catch -> Cleaner
		try {
			test.run();
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		/* Universal Exception Catcher
		try {
			test.run();
		} catch (Exception e) {
			System.out.println("I can catch any exception!");
		} 
		*/
		
		try {
			test.input();
		} catch (FileNotFoundException e) { 
			//FileNotFoundException is handled first, because it is a child class of IOException.
			//Putting IOException first will cause IOException to catch the FileNotFoundException too, making it confusing to know which Exception it really is.                                
			//Due to Polymorphism
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
			
		

	}

}
