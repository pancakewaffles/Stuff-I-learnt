import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
	private Account acc1 = new Account();
	private Account acc2 = new Account();
	
	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();
	
	private void acquireLocks(Lock firstLock, Lock secondLock){
		while(true){ // making sure that the locks are really distributed
			// Acquire locks
			
			boolean gotFirstLock = false;
			boolean gotSecondLock = false;
			
			try{
				gotFirstLock = firstLock.tryLock();
				gotSecondLock = secondLock.tryLock();
			}
			finally{
				if(gotFirstLock && gotSecondLock){
					return;
				}
				// If I haven't got both locks, then I check each one and unlock it because I want to give other threads a chance to get that lock.
				if(gotFirstLock){
					firstLock.unlock();
				}
				if(gotSecondLock){
					secondLock.unlock();
				}
			}
			
			// Locks not acquired
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}

	public void firstThread() throws InterruptedException {
		
		Random random = new Random();
		
		for(int i =0;i<10000;i++){
			//lock1.lock();
			//lock2.lock();
			
			acquireLocks(lock1,lock2);
			
			try{
			Account.transfer(acc1, acc2, random.nextInt(100));
			}finally{
				lock1.unlock();
				lock2.unlock();
			}
		}
		
	}

	public void secondThread() throws InterruptedException {
		Random random = new Random();
		for(int i =0;i<10000;i++){
			// If, we want to lock both locks in a different order than from firstThread()...
			//lock2.lock(); // I mean if we are transferring from Acc2 to Acc1 it makes sense to lock Acc2 first, right?
			//lock1.lock();
			// Then we run into the deadlock problem. Our program just waits, and waits.
			
			/*
			 * The reason this occurs, is because when firstThread() and secondThread() both run, 
			 * firstThread() runs lock1.lock() and acquires lock 1, and secondThread() runs lock2.lock() and acquires lock 2.
			 * Then firstThread() runs lock2.lock() but it can't, because it is acquired by secondThread(). Same for secondThread().
			 * So neither of the threads can proceed because each thread needs a thread that the other thread has.
			 * This is deadlock. It can occur with any type of locks, not just reentrantlocks (reentrantlocks just provides neat solution for deadlock), but also synchronized code blocks, when you use two locks.
			 * 
			 * Two solutions:
			 * 1. Always lock the locks in the same order. (lame.)
			 * 2. Write a method to distribute the locks.
			 * 
			 */
			
			acquireLocks(lock2,lock1); // note order
			
			try{
			Account.transfer(acc2, acc1, random.nextInt(100));
			}finally{
				lock1.unlock();
				lock2.unlock();
			}
		}
		
	}

	public void finished() throws InterruptedException {
		System.out.println("Account 1 balance: " + acc1.getBalance());
		System.out.println("Account 2 balance: " + acc2.getBalance());
		System.out.println("Total balance: " + (acc1.getBalance()+acc2.getBalance()));
		
		/*
		 * Note that total balance =/= 20000, which it should be. 
		 * Solutions:
		 * 1. Synchronized keyword --> To make sure only one transaction at any one time.
		 * 2. Use two reentrantlocks, one for each account.
		 */
	}

}
