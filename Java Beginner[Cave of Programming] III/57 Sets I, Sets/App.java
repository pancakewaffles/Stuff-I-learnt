import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/*
 * Set: a kind of collection that stores only unique elements
 */
public class App {
	
	public App() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		//HashSet does not retain order. It will spontaneously rearrange itself.
		//Set<String> set1 = new HashSet<String>();
		
		//LinkedHashSet remembers the order you added items in
		//Set<String> set1 = new LinkedHashSet<String>();
		
		//TreeSet sorts in natural order (in this case, alphabetical order)
		Set<String> set1 = new TreeSet<String>();
		
		set1.add("dog");
		set1.add("cat");
		set1.add("mouse");
		set1.add("snake");
		set1.add("bear");
		
		//Adding duplicate items does nothing.
		set1.add("mouse");
		
		System.out.println(set1);
		
		////// Iteration //////////////
		
		for(String element :set1){
			System.out.println(element);
		}
		
		////// Does set contain a given item? /////////
		if(set1.contains("aardvark")){
			System.out.println("Contains aardvark");
		}
		if(set1.contains("cat")){
			System.out.println("Contains cat");
		}
		
		///// Check if empty //////////
		if(set1.isEmpty()){
			System.out.println("Set is empty");
		}
		
		/////////////// Intersection ////////////////
		Set<String> set2 = new TreeSet<String>();
		
		set2.add("dog");
		set2.add("cat");
		set2.add("giraffe");
		set2.add("monkey");
		set2.add("ant");
		
		Set<String> intersection = new HashSet<String>(set1); //HashSet is the most lightweight type of Set
		                                                      //Making a copy of set1
		
		intersection.retainAll(set2); // Set1 n Set2
		
		System.out.println(intersection); //HOLY CRAP!
		
		
		/////////// Difference ////////////////////////
		Set<String> difference = new HashSet<String>(set1);
		
		difference.removeAll(set2); // Set1 - (Set1 n Set2)
		
		System.out.println(difference);
		
	}

}
