import java.util.Iterator;
import java.util.LinkedList;

public class App {

	public App() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		LinkedList<String> animals = new LinkedList<String>();
		
		animals.add("fox");
		animals.add("cat");
		animals.add("dog");
		animals.add("rabbit");
		
		/// Old-School Method of Iteration ///
		Iterator<String> it = animals.iterator(); //Collections implement the Iterator interface
		while(it.hasNext()){
		String value = it.next();
		System.out.println(value);
		if(value.equals("cat")){
			it.remove(); //Remove a specific item; Can't do this for a normal foreach loop
			}            //If you want to remove items while iterating, you have to use the Iterator method.
		                 //You can't add though. You have to use a ListIterator().
		
		}
		
		System.out.println("");
		
		/// Modern Iteration ///
		for(String animal:animals){
			System.out.println(animal);
		}

	}

}
