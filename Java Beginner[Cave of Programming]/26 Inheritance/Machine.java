
public class Machine {
	
	private String name = "Machine Type 1"; //this variable can only be accessed in the Machine class
	protected String identifier = "Machine A"; //means you can access this anywhere from within the package and the child class
	
	public void start(){
		System.out.println("Machine started.");
	}
	public void stop(){
		System.out.println("Machine stopped.");
	}

	public Machine() {
		// TODO Auto-generated constructor stub
	}

}
