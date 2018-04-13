//private class Tool { } Won't work because public, private and protected only works for variables
//You can have class Tool { } This makes the class accessible only within this file. So you can have many classes within a single file, but you can only have one public class in one file
public class App {

	public App() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Plant plant = new Plant();
		System.out.println(plant.ID); // public variable: accessible from anywhere
		System.out.println(plant.size); //protected variable: accessible from a class in the same package
										//App is in the same package as Plant
	}

}

