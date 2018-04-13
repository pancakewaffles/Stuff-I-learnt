/*
 * Interfaces create a special connection between different classes
 * It is a medium through which variables can be passed to different classes
 * It is a map of how objects interact with each other
 * 
 * 
 */
public class App {

	public App() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Machine mach1 = new Machine();
		mach1.start();
		
		Person person1 = new Person("Bob");
		person1.greet();
		
		Info info1 = new Machine(); //I can only just use the one method defined in the Interface
		//variables of the interface type
		info1.showInfo(); //I cannot use any other methods of Machine
		
		Info info2 = person1; //i.e. you just want a certain set of functions from the class e.g. Bank has many functions, but you only want the deposit function 
		info2.showInfo();
		
		outputInfo(person1); // i.e. taking the Info part from person1
		outputInfo(mach1); // So you can pass any class that implements the Info interface
	}
	
	private static void outputInfo(Info info){
		info.showInfo();
	}

}
