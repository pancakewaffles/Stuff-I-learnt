import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker {
	private Random random = new Random(); // Just a random number generator
	
	private Object lock1 = new Object();
	private Object lock2 = new Object();
	
	private List<Integer> list1 = new ArrayList<Integer>();
	private List<Integer> list2 = new ArrayList<Integer>();
	
	/*
	 * If we use the synchronized keyword, we would take 4 seconds instead of 2.
	 * This is because for each method, each thread has to wait till the other thread has finished calling it - that is what the synchronized keyword does.
	 * so if running both threads concurrently yields 2 seconds, then running it with synchronized methods will definitely take 4 seconds.
	 * 
	 * Okay, so that's the question of 4 seconds.
	 * 
	 * Now here's the problem.
	 * There is only 1 intrinsic lock for the Worker class. So, if Thread 1 runs stageOne(), Thread 2 has to wait - it can't run stageTwo(), despite the fact that stageOne() and stageTwo() are independent from each other.
	 * So what we want is to have a system whereby no two threads can run stageOne() at the same time, and no two threads can run stageTwo() at the same time.
	 * Right now the system is such that only one Thread can ever run either stageOne or stageTwo at the same time.
	 * That's also why it takes 4 seconds. Thread 2 is literally waiting for Thread 1 to finish both stageOne and stageTwo before launching its own stageOne and stageTwo.
	 * 
	 * The solution: Create two object locks (basically just objects) and use synchronized code blocks
	 * 
	 */
	
	public void stageOne(){ // I removed the synchronized keyword
		
	//Instead of synchronized keyword, we use the synchronized code block
		synchronized(lock1){ // You can lock on the lists themselves too, but it's better practice to use separate locks.
		try {
			Thread.sleep(1); // Just to simulate getting information from somewhere; a slowing down
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list1.add(random.nextInt(100));
		
		}
		
		
		
	}
	public void stageTwo(){ // I removed the synchronized keyword
		
		synchronized(lock2){
		try {
			Thread.sleep(1); // Just to simulate getting information from somewhere; a slowing down
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list2.add(random.nextInt(100));
		}
		
	}
	
	public void process(){
		for(int i =0;i<1000;i++){
			stageOne();
			stageTwo();
		}
	}

	public void main() {
		System.out.println("Starting...");
		
		long start = System.currentTimeMillis();
		
		//process();
		
		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				process();
				
			}
			
		});
		t1.start();
		
		Thread t2 = new Thread(new Runnable(){ // Running another Thread.

			@Override
			public void run() {
				process();
				
			}
			
		});
		t2.start();
		try {
			t1.join(); // Remember, we have to wait till the thread is finished. 
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
		long end = System.currentTimeMillis();
		
		System.out.println("Time taken: " + (end-start));
		System.out.println("List1: "+list1.size()+ "; List2: "+list2.size());
		
		//Will take around 2 seconds because each stage waits 1 ms, and two stages 2 ms. 1000 times, 2000 ms = 2 seconds.
	
		
	}

}
