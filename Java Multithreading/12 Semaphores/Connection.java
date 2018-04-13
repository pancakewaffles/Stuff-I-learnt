import java.util.concurrent.Semaphore;

public class Connection {
	private static Connection instance = new Connection();
	
	private Semaphore sem = new Semaphore(10,true); // Let's see how we can limit the number of connections
	                                          // This here is a fairness variable. This means that whichever thread wants to acquire the permit first, will be the first to get it. 
	private int connections = 0;
	
	private Connection(){
		
	}
	public static Connection getInstance(){
		return instance;
	}
	public void connect(){
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
			doConnect();
		}finally{
			sem.release();
		}
		
		
		
	}
	public void doConnect(){
		
		synchronized(this){
			connections++;
			System.out.println("Current connections: " + connections);
			
		}
		
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		synchronized(this){
			connections--;
			//System.out.println("Current connections: " + connections);
			
		}
		
	}
}
