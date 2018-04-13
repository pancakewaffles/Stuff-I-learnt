/*
 * Two kinds of Exceptions in Java: Runtime and Checked Exceptions
 * Checked Exceptions: Exceptions that we are forced to handle
 *                     We have seem them in Exceptions I and Exceptions II
 * Runtime Exceptions: AKA Unchecked Exceptions, which we do not need to handle immediately
 */


public class App {


	public static void main(String[] args) {
		
		int value = 7;
		value = value/0; //ArithmeticException is an example of Runtime Exception
		                 //We need not handle it in order for the class to compile
		                 //RuntimeExceptions suggest errors that are really serious!
		
		String text = null;
		
		System.out.println(text.length()); //NullPointerException: Variable that is referencing nothing
		
		String[] texts = {"one","two","three"};
		System.out.println(texts[3]);  //ArrayIndexOutOfBoundsException
		
		
		//You can still catch RuntimeExceptions if you want to! But you need not to, because you are expected to fix them.	
		try{
			System.out.println(texts[3]);
		} catch(Exception e){
			System.out.println(e.toString());
		}

	}

}
