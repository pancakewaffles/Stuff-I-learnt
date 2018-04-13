import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * Dummies' Guide to Reading Text Files
 */
public class App {

	public App() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws FileNotFoundException {
		//String fileName = "F:\\Users\\Chavez Tan\\Desktop\\example.txt"; 
		//Note the Double Backslash
		
		String fileName = "example.txt"; // Reading from the main project folder, not from the 37 Reading Files using Scanner folder
		
		File textFile = new File(fileName); // Java Object of your file
		
		//Instead of using BufferedReader, we shall use the Scanner class
		
		Scanner in = new Scanner(textFile); //throw exception = program stops when there is a FileNotFoundException
		
		int value = in.nextInt();
		System.out.println("Value: "+value);
		
		in.nextLine(); // Reading the invisible carriage return and line feed
		               // This only happens with numbers
		
		int count=2;
		while(in.hasNextLine()){
			
			String line = in.nextLine();
			System.out.println(count + ": "+line);
			count++;
		}
		
		in.close(); //Always remember!
		

	}

}
