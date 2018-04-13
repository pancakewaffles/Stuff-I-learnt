import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ReadObjects {

	public static void main(String[] args) {
		System.out.println("Reading objects...");
		
		try(ObjectInputStream os = new ObjectInputStream(new FileInputStream("people.trans"))){ //TRICK 2.0 : Do everything in one line
			
			
		
			Person person = (Person)os.readObject();
			System.out.println(person);
			//You will see id=0 --> That is the default value for top level fields
			//count, the static variable, returns 0 because it is a top level field and that static variables are not serializable
	
			
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot find file!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot read from file!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Reading an object that isn't defined!");
		}
		
	
	}

}
