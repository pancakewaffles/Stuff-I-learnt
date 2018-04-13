import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
	
	private int count =0;
	private Lock lock = new ReentrantLock(); // WOW. Much intimidate.
	// Reentrant locks are locks which keeps count of how many times it has been locked. To unlock it, you need to unlock it the number of times it has been locked.
	// Normally you just lock it once.
	
	private Condition cond = lock.newCondition();
	/*
	 * Okay, what the hell is Condition?
	 * We have analagous methods of wait(), notify() and notifyAll() for ReentrantLocks, called await(), signal() and signallAll() respectively. 
	 * But they are part of the class called Condition, which all ReentrantLocks have.
	 * We are getting the Condition from the ReentrantLock, and playing around with it.
	 */
	
	private void increment(){
		for(int i = 0;i<10000;i++){
			count++;
		}
	}
	
	public void firstThread() throws InterruptedException{
		
		lock.lock();
		
		System.out.println("Waiting...");
		cond.await(); // Basically will just unlock the lock, and pass the lock over.
		
		System.out.println("Woken up!");
		try{
		increment(); // Not a good way to do things, because if this code here throws Exception, the lock will never be unlocked.
		}finally{
		lock.unlock(); // So you should always use finally to ensure that the lock is unlocked.
		}
	}
	public void secondThread() throws InterruptedException{
		
		Thread.sleep(1000);
		
		lock.lock();
		
		System.out.println("Press the return key!");
		new Scanner(System.in).nextLine();
		System.out.println("Got return key!");
		
		cond.signal(); // Pass back the lock.
		
		try{
		increment();
		}finally{
			System.out.println("Thread 2 finished. So you can see that signal() works like notify(); it actually runs code after signal(), "
					+ "so the lock is not really passed back until Thread 2 is finished.");
			System.out.println("At this point, count: "+count);
		lock.unlock();
		}
	}
	public void finished(){
		System.out.println("Count is: "+count);
	}

}
