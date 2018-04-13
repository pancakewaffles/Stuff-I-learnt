/*
 * Problem 2: Threads entering and leaving
 */
public class App {
	
	private int count = 0;
	
	public synchronized void increment(){
		count++;
	}
	public static void main(String[] args) {
		App app = new App();
		app.doWork();
		

	}
	
	public void doWork(){
		
		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				for(int i = 0; i<10000;i++){
					increment();
				}
				
			}
			
		});
		
		Thread t2 = new Thread(new Runnable(){

			@Override
			public void run() {
				for(int i = 0; i<10000;i++){
					increment();
				}
				
			}
			
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join(); // Basically a wait-till-thread-finishes method 
			t2.join(); // !!!But WAIT!!!! It still doesn't output 20000. Count is still changing! Why?
			/*
			 * The reason is because when we do count++
			 * we are doing it in 3 steps: count = count + 1
			 * 1. Get the value of count
			 * 2. Add 1 to it
			 * 3. Store it back
			 * In human terms, those 3 steps happen very quickly.
			 * But in computer terms, they happen very slowly, because the computer is running other things while those 3 steps are still running.
			 * 
			 * Think about what happens if two threads are trying to do that at the same time.
			 * Imagine count = 100.
			 * Thread A grabs count as 100. Thread A adds 1 to count. Thread B grabs count as 100. Thread A stores count as 101. Thread B adds 1 to count. Thread B stores count as 101.
			 * So some increments are being skipped because both threads are reading the same value of count.
			 * 
			 * So we need to find some way to make it such that while one thread is modifying count, no other thread can touch count and change it.
			 * The simplest way to do this is to make count an atomic integer. Volatile doesn't work.
			 * Solution:
			 * We create a new method called increment, in which count++ resides. This alone will not help, because what's important is the next step,
			 * we add the synchronized keyword to the method increment.
			 * 
			 * Every object in Java has an intrinsic lock (aka monitor lock). If you call a synchronized method of an object, you have to acquire the intrinsic lock before you can call it.
			 * The trick is that only one thread can acquire the intrinsic lock at any one time. The second thread has to wait till the first thread released the intrinsic lock.
			 */
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("Count is: "+count); // Problem here is that we are outputting the value of count while both threads are still running (i.e. changing the value of count)
		                                        // So if you press run a few times, you get different values of count.
		
	}

}
