/*
 * Anonymous classes are basically a way of either extending an existing class or implementing an interface
 * 
 */
class Machine{
	public void start(){
		System.out.println("Starting machine...");
	}
}

interface Plant{
	public void grow();
	
}


public class App {

	public App() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		Machine machine1 = new Machine() {                    //Anonymous class
			@Override public void start(){                    //machine1 is a variable of type Machine, but of anonymous class
				System.out.println("Camera snapping...");  
			}
		};                                                        
		
		machine1.start();
		
		
		//Plant plant1 = new Plant();  is illegal because Plant is not a class, it is an interface
		Plant plant1 = new Plant(){ //Anonymous class to implement an interface

			@Override
			public void grow() {
				System.out.println("Plant growing");
				
			}    
			
		};
		
		plant1.grow();
		

	}

}
