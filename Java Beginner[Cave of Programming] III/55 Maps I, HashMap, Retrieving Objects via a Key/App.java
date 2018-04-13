import java.util.HashMap;
import java.util.Map;

/*
 * HashMaps: next most useful things after Lists
 * NOTE: HashMaps are not sorted in any way. So if you iterate through and you get this order,
 * the next time you iterate you may get a different order.
 */
public class App {

	public App() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		HashMap<Integer,String> map = new HashMap<Integer,String>();
		
		map.put(5, "Five");
		map.put(8, "Eight");
		map.put(6, "Six");
		map.put(4, "Four");
		map.put(2, "Two");
		
		map.put(6,"Hello"); //You cannot have duplicate keys, you only overwrite them
		String text = map.get(4);
		
		String text2 = map.get(1); // returns null
		
		System.out.println(text);
		
		for(Map.Entry<Integer, String> entry: map.entrySet()){ //Complicated!
			int key = entry.getKey();
			String value = entry.getValue();
			
			System.out.println(key + ": "+value);
		}
		
	}

}
