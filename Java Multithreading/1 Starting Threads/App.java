/*
 * Threads are separate operating system processes that can run concurrently with each other
 * 
 * Two ways to start a thread:
 * 
 * 1. Extending the Thread Class
 * 
 * 2. Implement Runnable and pass it to the constructor of the thread class.
 */

// Method 1: Extending the Thread Class
class Runner extends Thread{

	@Override
	public void run() {
		for(int i=0;i<10;i++){
			System.out.println("Hello " + i);
			
			try {
				Thread.sleep(100); // Slowing down our thread, for demostration's purpose.
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			} 
		}
	}
	
}
public class App {

	public static void main(String[] args) {

		Runner runner1 = new Runner();
		runner1.start(); 
		
		/*
		 * IMPORTANT! We HAVE to use runner1.start() and not runner1.run(), because start() tells the Thread class to look for the run method, and run that in its own special thread.
		 * Calling runner1.run() directly will call run() in the main() method, i.e. we are not using threads then.
		 */
		
		Runner runner2 = new Runner();
		runner2.start(); // Showing that they run in different threads.
		
		/*
		 * Both loops are running concurrently:
		 * Hello 0
		 * Hello 0
		 * instead of
		 * Hello 0 
		 * Hello 1
		 * Hello 0
		 * Hello 1
		 */

	}

}
