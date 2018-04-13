/*
 * An extension of 42 Reading Files II
 * Here we simplify all those exception handling code and make our code look cleaner
 * This can actually be done without using files, it just so happens that it is super useful with files
 */

class Temp implements AutoCloseable{

	@Override
	public void close() throws Exception { //Because we implemented AutoCloseable
		
		System.out.println("Closing!");
		throw new Exception("Oh no!");
	}
	
}
public class App {

	public App() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {

		try(Temp temp = new Temp()){ //Try-With-Resources
			//AutoCloseable means that the class autocloses, so we do not have to worry about catching
			//exceptions related to closing
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	

	}

}
