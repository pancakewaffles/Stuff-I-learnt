import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/*
 * Queues
 * 
 */
public class App {

	public App() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		/* (head) <- ooooooooooooooooo <- (tail) FIFO (first in, first out)
		the sooner you enter the queue, the sooner you can leave
		Queues are like LinkedLists
		But LinkedLists can have infinite size, while Queues, especially ArrayBlockingQueues, have a fixed size.
		*/
		Queue<Integer> q1 = new ArrayBlockingQueue<Integer>(3); // 3 being the max size
		
		/*
		 * ArrayBlockingQueues are useful for multithreading purposes. ArrayBlockingQueues implements the BlockingQueue interface, which contains a set of methods that can wait for each other.
		 * So one thread can wait to add items to it while the other can wait to remove from it.
		 * This is very useful in multithreading programs in which one thread can wait till the queue is empty/full before adding/removing items from it.
		 */
		
		q1.add(10);
		q1.add(20);
		q1.add(30);
		
		System.out.println("Head of queue is: "+q1.element()); //Peek at the next element in the queue
		
		try{
			q1.add(40);
		} catch(IllegalStateException e){ //This is a Runtime Exception
			System.out.println("Tried to add too many items to the queue.");
		}
		
		for(Integer value:q1){
			System.out.println("Queue 1 value: "+value);
		}
		
		Integer value = q1.remove(); //Removes from head of queue, and assign it to a variable
		System.out.println("Removed from queue: " +value);
		System.out.println("Removed from queue: " +q1.remove());
		System.out.println("Removed from queue: " +q1.remove());
		
		try{
		System.out.println("Removed from queue: " +q1.remove());
		}catch(NoSuchElementException e){ //Another runtime exception
			System.out.println("Tried to remove too many items from queue.");
		}
		
		/////////////////////////////////////////////////////////////////////////////
		
		Queue<Integer> q2 = new ArrayBlockingQueue<Integer>(2);
		
		System.out.println("Queue 2 peek: " + q2.peek()); //returns null instead of throwing exceptions.
		
		q2.offer(10);
		q2.offer(20);
		q2.offer(30); //No exceptions thrown because offer() is a boolean method that returns false when it can't add the item
		
		
		
		for(Integer element:q2){
			System.out.println("Queue 2 value: "+element);
		}
		
		System.out.println("Queue 2 poll: "+q2.poll());
		System.out.println("Queue 2 poll: "+q2.poll());
		System.out.println("Queue 2 poll: "+q2.poll()); //returns null. Poll() is like remove() except instead of throwing exceptions it returns null
		
		
	}

}
