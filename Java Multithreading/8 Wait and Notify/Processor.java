import java.util.Scanner;

public class Processor {
	
	public void produce() throws InterruptedException {
		synchronized(this){
			System.out.println("Producer thread running .... ");
			wait();
			System.out.println("Resumed.");
			/*
			 * wait() --> May cause your thread to wait indefinitely
			 * wait(1000) --> Wait 1 second.
			 * Every object in Java has a wait() method; it is a method of the Object class.
			 * wait() just waits, without consuming much resources. (vs while loop that keeps checking flags)
			 * IMPORTANT! You can only call wait() within synchronized codeblocks, because it removes the intrinsic lock of the synchronized code block.
			 * Therefore whatever code that is below wait() will not run, until it regains the intrinsic lock, via the notify() method.
			 */
		}
	}
	public void consume() throws InterruptedException {
		Scanner scanner = new Scanner(System.in);
		Thread.sleep(2000); // So that the thread running produce() has a chance to kick off first.
		
		synchronized (this){ // It gets the intrinsic lock because wait() of produce() removed the intrinsic lock from produce().
			
			System.out.println("Waiting for return key.");
			scanner.nextLine();
			System.out.println("Return key pressed.");
			notify();
			/*
			 * notify() --> Notify one thread waiting for the SAME intrinsic lock
			 * notifyAll() --> Notifies ALL threads waiting for that particular intrinsic lock
			 * Same thing with wait(); notify() can only be called within a synchronized code block.
			 * notify() tells the waiting thread that it can pick up the intrinsic lock again.
			 * But it does not remove the intrinsic lock of consume(), so we need notify() to be the last code in the synchronized code block.
			 */
			System.out.println("This is running after notify(). Shows that notify() does not remove the intrinsic lock.");
			
			
		}
	}

}
