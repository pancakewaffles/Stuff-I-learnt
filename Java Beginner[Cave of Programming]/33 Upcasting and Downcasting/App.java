//Casting is a tricky subject
class Machine{
	public void start(){
		System.out.println("Machine started.");
	}
}

class Camera extends Machine{
	public void start(){
		System.out.println("Camera started.");
	}
	public void snap(){
		System.out.println("Photo taken.");
	}
}


public class App {

	public App() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {

		Machine machine1 = new Machine();
		Camera camera1 = new Camera();
		
		//Upcasting
		Machine machine2 = camera1; //Polymorphism + Upcasting
		//Upcasted variable camera1 to a Machine; up ->Upgraded Child to Parent Class
		
		machine2.start(); //Because the actual object machine2 refers to is a Camera
		
		//machine2.snap(); //What? Why? Because it's the actual variable that decides what methods to call
		/*So, machine2 is a variable of type Machine, hence it inherits its methods.
		 * But machine2 is a reference to an object of type Camera,
		 *  hence it inherits any overriding methods, but not all its methods.
		 */
		
		//Downcasting
		Machine machine3 = new Camera(); //i.e. machine3.snap() wouldn't work
		Camera camera2 = (Camera)machine3; //Down-> Parent to Child class; Only for Downcasting do you need to put (Camera)
		camera2.snap();
		
		//Downcasting is inherently unsafe
		Machine machine4 = new Machine();
		Camera camera3 = (Camera)machine4;
		camera3.start(); //Fails because machine4 is a Machine object , not a Camera object
		                 //Is a runtime error
		
		
		
	}

}
