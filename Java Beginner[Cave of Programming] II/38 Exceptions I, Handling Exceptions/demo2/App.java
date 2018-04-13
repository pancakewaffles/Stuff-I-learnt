package demo2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class App {

	public App() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		try { 
			openFile();      //So you have to throw it out of the main method too
                             //Or handle it using Try/Catch --> Better; Don't just throw exceptions around! They are ugly!
		} catch (FileNotFoundException e) {
			System.out.println("Could not open file.");
		} 

	}
	public static void openFile() throws FileNotFoundException{ //Throw it out into the main method
		File file = new File("test.txt");
		
		FileReader fr = new FileReader(file);
	}

}
