import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ReadObjects {

	public static void main(String[] args) {
		System.out.println("Reading objects...");
		
		try(FileInputStream fi = new FileInputStream("people.arr");ObjectInputStream os = new ObjectInputStream(fi)){ //TRICK: Multiple auto-closable objects!
			
			
			Person[] people = (Person[])os.readObject(); 
			
			@SuppressWarnings("unchecked")
			ArrayList<Person> peopleList = (ArrayList<Person>)os.readObject();
			/*
			 * Type Erasure Problem:
			 * With parameterized classes (i.e. ArrayLists), they suffer from type erasure,
			 * a condition in which information about the type inside the < > is lost.
			 * Easiest thing to do is to suppress it.
			 */
			
			
			
			for(Person person : people){
				System.out.println(person);
			}
			for(Person person : peopleList){
				System.out.println(person);
			}
			
			//Reading the objects of the ArrayList individually
			int num = os.readInt();
			for(int i =0;i<num;i++){
				Person person = (Person) os.readObject();
				System.out.println(person);
			}
			
			
			
			//os.close(); Don't really need this now.
			
			
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
