import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class WriteObjects {

	public static void main(String[] args) {
		
		System.out.println("Writing objects...");
		
		
		try(ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("people.trans"))){ //TRICK 2.0 : Do everything in one line
			
			Person.setCount(88);     
			Person person = new Person(7,"Bob");
			
			os.writeObject(person);
			
			
		
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot Open File!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot write to File!");
		}
		
	}

}
