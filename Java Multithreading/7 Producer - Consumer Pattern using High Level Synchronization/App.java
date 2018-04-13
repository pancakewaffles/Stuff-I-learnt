import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/*
 * Producer-Consumer Pattern and the BlockingQueue interface.
 * 
 */
public class App {
	/*
	 * BlockingQueue is a FIFO queue.
	 * Right we are in the multithreading course right? Yea here's the point.
	 * Classes in "concurrent" package are thread-safe, so we don't have to worry about thread synchronization.
	 * i.e. BlockingQueues are thread-safe.
	 */
	private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10); // 10 represents the max size

	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable(){ // The Producer

			@Override
			public void run() {
				try {
					producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});
		
		Thread t2 = new Thread(new Runnable(){ // The Consumer

			@Override
			public void run() {
				try {
					consumer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	/*
	 * The Producer - Consumer Pattern works like this.
	 * The Producer passes on goods ("tasks") to a queue, while The Consumer consumes them from the queue.
	 * 
	 * Example: Text Message Service
	 * Users (Producer) send messages to a server (queue) from which other users (Consumer) receive them.
	 */
	
	private static void producer() throws InterruptedException{
		Random random = new Random();
		
		while(true){
			queue.put(random.nextInt(100)); // put() actually waits for items to be taken out of the queue if the queue is full. So you don't have to worry about the queue being full.
			
		}
	}
	
	private static void consumer() throws InterruptedException{
		Random random = new Random();
		
		while(true){
			Thread.sleep(100); // Only consumes once in a while
			
			if(random.nextInt(10) == 0){ // Simulating taking items only as fast as it is able to.
				Integer value = queue.take(); // take() actually waits for the queue to have something before it takes from it. So you don't have to worry about the queue being empty.
				
				System.out.println("Taken value: "+value + "; Queue size is; " + queue.size());
				
				
			}
		}
	}

}
