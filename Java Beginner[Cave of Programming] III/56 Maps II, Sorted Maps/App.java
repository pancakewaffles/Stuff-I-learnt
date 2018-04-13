import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/*
 * Sorted Maps = Hash Maps that maintain order
 */
public class App {

	public App() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Map<Integer,String> hashMap = new HashMap<Integer,String>();
		//<-Putting Map is good practice. Channels the idea of "From a common interface to a specific type"
		
		
		//Sorted Maps
		Map<Integer,String> linkedHashMap = new LinkedHashMap<Integer,String>();
		     //= HashMaps + Links
		
		Map<Integer,String> treeMap = new TreeMap<Integer,String>();
			// Tree sorts the values in the natural order
			// Natural Order is a tightly defined concept in Java
			// Natural Order : Alphebetical Order, Numerical Order etc
		System.out.println("HashMap:");
		testMap(hashMap); //No guarantee to order
		System.out.println("LinkedHashMap:");
		testMap(linkedHashMap); //As is
		System.out.println("TreeMap:");
		testMap(treeMap); //Natural Order
	}
	
	public static void testMap(Map<Integer,String> map){ //Because all implement the map interface
		map.put(9,"fox");
		map.put(4,"cat");
		map.put(8,"dog");
		map.put(1,"giraffe");
		map.put(0,"swan");
		map.put(15,"bear");
		map.put(6,"snake");
		
		for(Integer key:map.keySet()){ //iterating over the keys to get the values
			//map.keySet() returns a Set of keys
			String value = map.get(key);
			System.out.println(key+": "+value);
		}
	}

}
