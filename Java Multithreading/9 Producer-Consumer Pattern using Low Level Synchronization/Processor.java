import java.util.LinkedList;
import java.util.Random;

public class Processor {
	
	private LinkedList<Integer> list = new LinkedList<Integer>();
	private final int LIMIT = 10;
	private Object lock = new Object();
	
	public void produce() throws InterruptedException { // add to list
		
		int value = 0;
		
		while(true){
			synchronized(lock){
				
				while(list.size()==LIMIT){ // if list is full, wait. You normally want to surround it with a while loop so that it will go back and check the while condition again.
					lock.wait(); // IMPORTANT! You need to call wait on the object you are locking on.
				}
				list.add(value++);
				lock.notify();
			}
		}
	}
	public void consume() throws InterruptedException { // remove from list
		
		Random random = new Random();
		
		while(true){
			synchronized(lock){
				
				while(list.size() == 0){ // if list is empty, wait.
					lock.wait();
				}
				System.out.print("List size is: "+list.size());
				int value = list.removeFirst(); // FIFO structure
				System.out.println("; value is: "+value);
				lock.notify();
			}
			
			Thread.sleep(random.nextInt(1000));

		}
	}

}
