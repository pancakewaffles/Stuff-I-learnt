/*
 * Polymorphism is an object-oriented concept meaning many shapes
 * i.e. References and Objects
 * 
 */
public class App {

	public App() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Plant plant1 = new Plant();
		Tree tree = new Tree();
		
		//Polymorphism means that if you have a child class of some parent class, 
		// you can always use the child class anywhere where you would normally use the parent class
		//In this e.g., tree is a child class of plant
		Plant plant2 = plant1; 
		Plant plant3 = tree; //Polymorphism in action
		
		plant2.grow();
		plant3.grow();
		
		//plant3.shedLeaves();  doesn't work because this method is in Tree rather than Plant.
		//It's the type of the variable that decides what methods you can call
		/* when you call a method, it will go to that method in that object, not the reference
			plant3 is a reference to type Tree, but an object of Plant
		*/
		doGrow(tree);
	}
	
	public static void doGrow(Plant plant){
		plant.grow();
	}

}
