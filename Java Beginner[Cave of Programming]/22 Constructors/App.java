//You can and should have multiple constructors
class Machine{
	private String name;
	private int code;
	
	public Machine(){
		System.out.println("Constructor running");
		name = "Arnie"; 
	}
	public Machine(String name){
		this.name = name;
		System.out.println("Second constructor running");
	}
	public Machine(String name, int code){
		this("dd"); // Calling the second constructor from the third constructor. Must be the first line
		//Machine("dd");   this doesn't work
		this.name = name;
		this.code = code;
		System.out.println("Third constructor running");
		//Constructors can always call another constructor
		// You can use the most complex constructor to call on the simpler constructor
		
	
	}
	
}
public class App {


	public static void main(String[] args) {
		Machine machine1 = new Machine();
		
		//new Machine();
		
		Machine machine2 = new Machine("Bertie");
		
		Machine machine3 = new Machine("Charlie",5);

	}

}
