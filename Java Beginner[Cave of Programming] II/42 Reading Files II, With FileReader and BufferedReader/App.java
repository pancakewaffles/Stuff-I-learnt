import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 * Old way of reading files
 */
public class App {

	public App() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args){
		
		File file = new File("test.txt");
		
		try {
			FileReader fr = new FileReader(file);
			//Buffering - Save up the characters we read from the file and process it as a line
			BufferedReader br = new BufferedReader(fr);
			
			String line;
			
			
			while((line = br.readLine()) != null){ //Reading all the lines
			
			System.out.println(line);
			
			}
			
			br.close();
			
			
		} catch (FileNotFoundException e) {
			System.out.println("File not Found: "+ file.toString());
		} catch (IOException e) {
			System.out.println("Unable to read file: "+ file.toString());
		}
		finally{ //The finally block will always be executed regardless whatever errors in the try block
			
		}
		
		
		
		
	}

}
