
public class App {

	public App() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Camera cam1= new Camera();
		cam1.setId(5);
		
		Car car1= new Car();
		car1.setId(4);
		
		//In this case, machine pretty much functions as a base class for Car and Camera
		//No other purpose whatsoever
		//There is no sense in creating a new Machine
		//To prevent ppl from doing that, we make the class Machine an abstract class
		
		//Machine machine1 = new Machine(); Will produce an error

	}

}
