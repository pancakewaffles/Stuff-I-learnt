import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * Linked Lists vs ArrayLists
 * Basic rule: If you want to add/remove only at the end of the list, use ArrayLists
 *             If you want to add/remove items from anywhere in the list, use LinkedLists
 * Why?
 * Because ArrayLists manage arrays internally.
 * [0][1][2][3][4][5]...
 * ArrayLists have a default size of 10. If you add in more than 10 items, the ArrayList
 * creates a new Array of size 20, and copy the old items into the new array. So it only does this occasionally,
 * and hence it is quite fast to add/remove items to the end of list.
 * 
 * Adding/Removing items to the beginning causes the ArrayList to move the items from the end, one by one to the next array, in order to create space in the beginning for a new element.
 * That is a slow process.
 * 
 * LinkedLists consists of elements where each element
 * has a reference to the previous and next element.
 * [0]->[1]->[2]...
 * 	  <-   <-	 
 * Traversing a LinkedList would be quite slow.
 * Adding/Removing an element anywhere in the list would be fast, because
 * you just need to point the "next" pointer to the new element, and "previous" pointer to the new element
 * 
 */
public class App {

	public App() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		List<Integer> arrayList = new ArrayList<Integer>(); 
		//Or ArrayList<Integer>...
		
		List<Integer> linkedList = new LinkedList<Integer>();
		//Or LinkedList<Integer>...
		
		doTimings("ArrayList",arrayList);
		doTimings("LinkedList",linkedList);
		
	}
	
	private static void doTimings(String type, List<Integer> list){ //Because they all implement the List interface
			
		for(int i=0;i<1E5;i++){
			list.add(i);
			
		}
		long start = System.currentTimeMillis();
		/*
		//Add items at end of list
		for(int i=0;i<1E5;i++){
			list.add(i);
			
		}
		*/
		
		//Add items to the close to the end of list
		for(int i =0;i<1E5;i++){
					list.add(list.size()-100, i); 
				}
		
		/*
		//Add items to the beginning of list
		for(int i =0;i<1E5;i++){
			list.add(0, i); 
		}
		*/
		

		
		
		long end = System.currentTimeMillis();
		System.out.println("Time taken: "+ (end-start) + " ms for "+type);
	}

}
