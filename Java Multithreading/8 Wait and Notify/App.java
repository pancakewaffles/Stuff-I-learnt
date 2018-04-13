/*
 * In the last tutorial we took a look at implementing the Producer-Consumer Pattern using the ArrayBlockingQueue, which is thread-safe.
 * That is some high level synchronization bs.
 * Now we shall look at how we can implement the Producer-Consumer Pattern using some low level synchronization bs.
 * 
 * But first, we have to learn wait() and notify().
 * 
 */
public class App {

	public static void main(String[] args) throws InterruptedException{

		final Processor processor = new Processor();
		
		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				try{
					processor.produce();
				}catch(InterruptedException e){
					e.printStackTrace();
				}
				
			}
			
		});
		
		Thread t2 = new Thread(new Runnable(){

			@Override
			public void run() {
				try{
					processor.consume();
				}catch(InterruptedException e){
					e.printStackTrace();
				}
				
			}
			
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		

	}

}
