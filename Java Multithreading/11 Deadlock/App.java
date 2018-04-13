/*
 * What is with these lock names? Jeez.
 * Wait for it...
 * In this tutorial, we shall be looking at the deadlock problem, which we can solve using the trylock method of the ReentrantLock class.
 * Oh my days.
 * The deadlock problem arises when we use two different locks, and we lock them in different order.
 */
public class App {

	public static void main(String[] args) throws Exception{

		final Runner runner = new Runner();
		
		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				try{
					runner.firstThread();
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable(){

			@Override
			public void run() {
				try{
					runner.secondThread();
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		runner.finished();


	}

}
