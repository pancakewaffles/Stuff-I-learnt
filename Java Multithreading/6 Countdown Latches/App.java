import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * There are many classes in Java that are thread-safe (i.e. multiple threads can access them without worrying about thread synchronization)
 * CountDownLatch is one of them.
 * A CountDownLatch is a great-to-know class. It allows you to count down from a number you specify.
 * It lets a thread wait till the count down goes to zero.
 * So one or more threads can count down the latch and when it finally equals zero then one or more threads waiting on the latch can then proceed.
 */

class Processor implements Runnable{
	
	private CountDownLatch latch; // Notice I am not using the "synchronized" keyword; this is a thread-safe class.
	
	public Processor(CountDownLatch latch){
		this.latch = latch;
	}
	@Override
	public void run() {
		System.out.println("Started.");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		latch.countDown();
	}
	
}
public class App {

	public static void main(String[] args) {

		CountDownLatch latch = new CountDownLatch(3);
		
		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		for(int i =0;i<3;i++){
			executor.submit(new Processor(latch));
		}
		
		try {
			latch.await(); // Waits until CountDownLatch has counted down to zero.
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		System.out.println("Completed.");
	}

}
