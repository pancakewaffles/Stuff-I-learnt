
public class Car extends Machine { //Car derives/inherits from Machine INHERITANCE
	//Final classes can't be inherited/extended

	public Car() {
		// TODO Auto-generated constructor stub
	}
	public void start(){ //Overriding the method in Machine
		System.out.println("Car started.");
	}
	
	public void wipeWindShield(){
		System.out.println("Wiping windshield.");
	}
	@Override //check that the overwritten method indeed exists in the parent class
				// Never override a variable; only override a method
	public void stop() {
		System.out.println("Car stopped.");
	}
	public void showInfo(){
		//System.out.println("My name is: "+name); there will be an error
	}
	

}
