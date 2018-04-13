// Method 2: Implement Runnable and pass it to the constructor of the Thread class.

class Runner2 implements Runnable{ // Runnable is an interface with only one method: public void run()

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

public class App2 {

	public static void main(String[] args) {
		Thread t1 = new Thread(new Runner2()); // NOTE! Passing to the constructor of the Thread class.
		Thread t2 = new Thread(new Runner2());
		
		t1.start();
		t2.start();
	}

}
