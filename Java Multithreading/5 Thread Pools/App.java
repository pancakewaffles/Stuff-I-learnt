import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
 * Thread pools are a way of managing lots of threads at the same time.
 */

class Processor implements Runnable{
	
	private int id;
	
	public Processor(int id){ // I will have lots of processors running at the same time so I want to keep track
		this.id = id;
	}

	@Override
	public void run() {
		System.out.println("Starting: " + id);
		
		try {
			Thread.sleep(5000);  //Simulating handling of some work
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Completed: " + id);
		
	}
	
}
public class App {

	public static void main(String[] args) {
		/*
		 * If I want to create 10 of Processors (simulate 10 different tasks), I can use ExecutorService to create a Thread Pool.
		 * Step 1: Create an ExecutorService to create a Thread Pool.
		 * So what is a Thread Pool?
		 * A Thread Pool is a group of workers that you have to help you process tasks.
		 * Each thread handles a task, and when it finishes a task, it moves on to a new task.
		 * 
		 * Tasks ===> Thread Pool (where tasks are handled by threads/workers)
		 * 
		 * Step 2: Allot the tasks
		 * 
		 * Step 3: Shut down the ExecutorService after it is done with the tasks.
		 * 
		 */
		
		// Step 1: Create the Thread Pool
		ExecutorService executor = Executors.newFixedThreadPool(2); // The 2 here represents the number of threads (or workers)
		
		// Step 2: Push the tasks into the Thread Pool
		for(int i=0;i<5;i++){
			executor.submit(new Processor(i)); // In case you haven't noticed, Processor is the task.
		}
		
		// Step 3: Shutdown the Thread Pool
		executor.shutdown();
		
		System.out.println("All tasks submitted.");
		
		try {
			executor.awaitTermination(1, TimeUnit.DAYS); // If your tasks didn't finish in 1 day, it will return and continue with the program. Sort of a safety catch.
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("All tasks completed.");

	}

}
