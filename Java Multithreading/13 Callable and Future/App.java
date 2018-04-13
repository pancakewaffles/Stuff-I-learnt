import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/*
 * Callable and Future are two classes that enable you to get return values from your thread, and to throw exceptions.
 */
public class App {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool(); // Create a Thread Pool
		
		executor.submit(new Runnable(){

			@Override
			public void run() {             // If I want to return a value from this thread... I would have to use Callable and Future classes.
				
				Random random = new Random();
				int duration = random.nextInt(4000);
				
				System.out.println("Starting ...");
				try {
					Thread.sleep(duration);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Finished.");
				
			}
			
		});
		
		///////////// Callable and Future Classes ////////////////////
		
		Future<Integer> future = executor.submit(new Callable<Integer>(){ // new Callable<"return type">(){}
			// Future is the class to which call() returns values to.
			@Override
			public Integer call() throws Exception { // Callable has a method called "call" which returns a value to a future.
				Random random = new Random();
				int duration = random.nextInt(4000);
				
				// Throwing Exceptions Protocol
				/*
				 * When an exception is thrown from a call method, it goes to the future.get() method, where it is caught as an ExecutionException
				 */
				if(duration >2000){
					throw new IOException("Sleeping for too long."); 
				}
				
				System.out.println("Starting ...");
				try {
					Thread.sleep(duration);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Finished.");
				
				return duration;
			} 
			
		});
		
		executor.shutdown();
		
		try {
			executor.awaitTermination(1, TimeUnit.DAYS); // Rather unnecessary though, because future.get() actually waits for future to collect the results.
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} 
		
		try {
			System.out.println("Result is: "+ future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			IOException ex = (IOException) e.getCause();
			System.out.println(ex.getMessage());
		}
		
		/*
		 * The methods of future
		 * future.get() --> Get return value.
		 * future.cancel() --> Cancel thread.
		 * future.isDone() --> Tells you whether your thread is finished yet.
		 * 
		 * If you want to use the methods of future but do not wish to return any value, you can do this
		 */
		ExecutorService executor2 = Executors.newCachedThreadPool();
		Future<?> future2 = executor2.submit(new Callable<Void>(){

			@Override
			public Void call() throws Exception {
				// TODO Auto-generated method stub
				return null;
			}
			
		});
		
		executor2.shutdown();
		

	}

}
