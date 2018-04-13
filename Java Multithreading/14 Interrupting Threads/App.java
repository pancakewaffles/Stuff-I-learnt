import java.util.Random;

/*
 * How to Interrupt a Thread. What are InterruptedExceptions. How can you deal with them?
 */
public class App {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Starting.");
		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				Random ran = new Random();
				for(int i=0;i<1E8;i++){ // 1E6 = 1 * 10^6
					System.out.println(i);
					/* One method of checking whether thread is being interrupted.
					if(Thread.currentThread().isInterrupted()){ // Check if current thread is interrupted.
						System.out.println("Interrupted!");
						break;
					}
					*/
					
					//Another method.
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) { // Because it catches InterruptedExceptions
						System.out.println("Interrupted! Caught from Thread.sleep(1).");
						break;
					}
					
					Math.sin(ran.nextDouble());
				}
				
			}
			
		});
		t1.start();
		
		Thread.sleep(500); // Basically, after 500ms, call t1.interrupt().
		
		t1.interrupt(); // Doesn't actually stop the thread; it just sets a flag that it is being interrupted.
						// Don't use t1.stop(); it is deprecated.
		t1.join();
		
		System.out.println("Finished.");

	}

}
