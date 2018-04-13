
public abstract class Machine { //Abstract class!
	private int id;
	
	public Machine() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	//Abstract methods - forcing all child classes to implement these methods, but not having the class Machine to implement these methods
	
	public abstract void start(); //Like how you would do it in an interface
	public abstract void doStuff();
	public abstract void shutdown();
	
	/*So when to use abstract class and when to use interface?
	 * Anything can implement an interface, but only a child class can implement the abstract methods of an abstract class
	 * A class can implement many interfaces, but a class can only extend one abstract class
	 * A strong statement of identity - the child class is a child of the parent class
	 * The child class gets its functionality from the abstract parent class, but it does not do so from an interface
	 * From an interface it obtains its form, but not the functionality, which has to be implemented in the child class itself.
	*/
	
	public void run(){
		start();
		doStuff();
		shutdown();
	}
}
