
public class Robot {

	private int id;
	
	// An Inner Class: Logically grouping sets of outer classes
	public class Brain { 
		public void think() {
			System.out.println("Robot " + id + " is thinking."); //Accessing instance data of the outer robot class with the inner class
		}
	}
	
	public static class Battery{ //Static inner classes cannot access the variable id
		public void charge(){    //Static inner classes are used when you don't want them to have access to instances of the enclosing outer class
			System.out.println("Battery charging..."); 
		}
	}

	public Robot(int id) {
		this.id = id;
	}

	public void start() {
		System.out.println("Starting robot: " + id);
		
		Brain brain = new Brain(); //Brain, Arm, Leg, Chest...can all be grouped as Inner Classes
		brain.think();
		
		//Declaring classes within methods
		
		final String name = "Robert";
		class Temp{ //Can only be used within the start() method
			public void doSomething(){
				System.out.println("ID is: "+id); //Can refer to instance data absolutely fine
				System.out.println("My name is "+name); //Can only refer to local data if it is final
			}
		}
		
		Temp temp = new Temp();
		temp.doSomething();
	}

}
