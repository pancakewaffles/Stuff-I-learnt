import java.util.ArrayList;
import java.util.HashMap;

class Animal{
	
}
public class App {

	public App() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		//Generics: An Example - ArrayList
		
		///////////Before the introduction of Generics///////////
		ArrayList list = new ArrayList();
		list.add("apple");
		list.add("banana");
		list.add("orange");
		String fruit = (String)list.get(1); //list.get() retuns an Object, so you need to downcast it to String 
		System.out.println(fruit);
		
		//////////Modern Style//////////
		
		ArrayList<String> strings  = new ArrayList<String>(); //<String> is introduced with Generics
		strings.add("cat");
		strings.add("dog");
		
		String animal = strings.get(1); //No need to downcast because you specified the type of object in ArrayList
		System.out.println(animal);
		
		
		///////////There can be more than one type of argument /////////
		HashMap<Integer,String> map = new HashMap<Integer,String>();
		
		/////////// Java 7 Style //////////
		//Basically just saves effort
		ArrayList<Animal> someList = new ArrayList<>(); //List of animals
		

	}

}
