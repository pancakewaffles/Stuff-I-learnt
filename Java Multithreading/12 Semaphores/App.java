import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/*
 * Semaphores.
 * A semaphore is an object that maintains a count. The count is known as available permits.
 * Semaphores can be used as locks. They are normally used to control some resources
 */
public class App {

	public static void main(String[] args) throws Exception {
		Semaphore sem = new Semaphore(1); // 1 is the count / available permits
		
		sem.release(); // increment count by 1 / release the permit back
		
		sem.acquire(); // decrease count by 1 / obtain a permit
		/*
		 * acquire() has an inherent wait() function, so it will actually wait for an available permit to be released if there is no available permit
		 */
		
		System.out.println("Available permits: "+sem.availablePermits());
		
		///////////////////////// Example of how to use a Semaphore ///////////////////////////////////
		// We shall use Semaphores to limit the number of connections at any given time.
		
		ExecutorService executor = Executors.newCachedThreadPool();
		//CachedThreadPool vs FixedThreadPool
		// CachedThreadPool has no fixed amount of workers, and can reuse threads to minimize the amount of workers.
		
		for(int i=0;i<200;i++){
			executor.submit(new Runnable(){ // 200 threads

				@Override
				public void run() {
					Connection.getInstance().connect(); // i.e. only ever one Connection object at any time
					
				}
				
			});
		}
		
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.DAYS);

	}

}
