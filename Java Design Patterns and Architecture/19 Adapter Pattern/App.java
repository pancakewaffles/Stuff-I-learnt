/*
 * The Adapter Pattern:
 * Mapping one interface to another. What?
 * 
 * Let's illustrate this with an example.
 */
public class App {

	public static void main(String[] args) {
		/*
		 * So we have a class logger that expects a class that implements the LogWriter interface.
		 * But we have a class ConsoleWriter that conducts the functionality that we want through the LogWriter interface.
		 * To solve this, we use an Adapter class. It will adapt ConsoleWriter class to the LogWriter interface.
		 * 
		 * Two ways to implement an Adapter class:
		 * 1. Composition
		 * 2. Inheritance
		 */
		
		///////////// Composition
		// In which the Adapter class does not extend the functionality class, but rather implements the interface and defines the interface method using the functionality class's method.
		ConsoleLogWriter logWriter = new ConsoleLogWriter();
		Logger logger = new Logger(logWriter);
		
		logger.write("Hello there.");
		
		///////////// Inheritance
		// In which the Adapter class extends the functionality class and implements the interface. Then it defines the interface method using the functionality class's method.
		ConsoleLogWriter2 logWriter2 = new ConsoleLogWriter2();
		Logger logger2 = new Logger(logWriter2);
		
		logger2.write("Hello there 2.");

	}

}
