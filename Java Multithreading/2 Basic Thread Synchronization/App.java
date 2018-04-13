import java.util.Scanner;

/*
 * Basic Thread Synchronization.
 * There are two kinds of problems that you will encounter if you have more than one thread showing the same data.
 * 1. Cached Data (we will be looking at this this tutorial)
 * 2. Threads entering and leaving (more vicious, we will look at it next tutorial)
 * 
 * volatile keyword, basic thread synchronization
 */

class Processor extends Thread{
	
	// The Cached Data problem is when the boolean running is somehow cached, and so it will never see the changed value.
	/* The problem is that Java likes to optimize its code, and so the two threads may have their own copies of running
	   i.e. The run() thread caches its own copy of running (which equals true) and the main() thread that modifies running, actually modifies its copy of running.
	   and the run() thread goes on believing that running = true.
	   Because shutdown() is called in the main() loop.
	   To prevent that from happening, we use the keyword "volatile"
	   "volatile" prevents threads from caching variables; useful for threads to modify variables of each other.
	*/
	private volatile boolean running = true;
	
	public void run(){
		
		while(running){
			System.out.println("Hello");
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void shutdown(){  // Some way of shutting down the loop
		running = false;
	}
}
public class App {

	public static void main(String[] args) {
		Processor proc1 = new Processor();
		proc1.start();
		
		System.out.println("Press return to stop ...");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		
		proc1.shutdown();
		

	}

}
