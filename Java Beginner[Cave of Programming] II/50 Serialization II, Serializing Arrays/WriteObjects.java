import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class WriteObjects {

	public static void main(String[] args) {
		
		System.out.println("Writing objects...");
		
	
		Person[] people = {new Person(1,"Sue"),new Person(99,"Mike"),new Person(7,"Bob")};
		//The array is serializable if the objects it contains are serializable
		
		//It also works for arraylists!
		ArrayList<Person> peopleList = new ArrayList<Person>(Arrays.asList(people)); //Arrays.asList converts an array to an arraylist
		
		
		
		
		try(FileOutputStream fs = new FileOutputStream("people.arr");ObjectOutputStream os = new ObjectOutputStream(fs)){  
			                                                            
			
			
			
			os.writeObject(people);
		
			os.writeObject(peopleList);
			
			//Writing the objects of the Arraylist individually
			os.writeInt(peopleList.size());
			
			for(Person person: peopleList){
				os.writeObject(person);
			}
			
			//os.close(); We don't need this now.
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot Open File!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot write to File!");
		}
		
	}

}
