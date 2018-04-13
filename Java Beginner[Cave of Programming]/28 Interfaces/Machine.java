
public class Machine implements Info { //implements forces you to inherit all of the methods with the headers as specified in the interface
										// a class can implement a lot of interfaces
	private int id = 7;
	
	public void start(){
		System.out.println("Machine started.");
		
	}
	public Machine() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void showInfo() {
		// TODO Auto-generated method stub
		System.out.println("Machine ID is: "+id);
	}

}
