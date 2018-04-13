/*
 * Enum = Enumerated
 */
public class App {
	/*
	public static final int DOG = 0;
	public static final int CAT = 1;
	public static final int MOUSE = 2;
	*/

	public static void main(String[] args) {
		
		//int animal = CAT; // I can set this integer to anyone of DOG, CAT or MOUSE
						  // Example: Different colors
		/*
		 * A few problems though:
		 * A) We need to know the full set of integers animal can take
		 * B) We need to know that DOG,CAT and MOUSE exists
		 * 
		 * Enum solves this by creating a fixed set, and enumerating through it
		 */
		
		Animal animal = Animal.CAT; //You can only assign values from the enum --> Makes it less error-prone
		
		switch(animal){
		case CAT:
			break;
		case DOG:
			break;
		case MOUSE:
			break;
		default:
			break;
	
		}
		//Some slightly more advanced uses of enum and other things
		System.out.println(Animal.DOG.getClass()); //They are objects of type Animal
		System.out.println(Animal.DOG instanceof Animal); //x instanceof y = is x an instance of y?
		System.out.println(Animal.MOUSE.getName());
		System.out.println(Animal.DOG);
		System.out.println("Enum name as a string: "+Animal.DOG.name()); //Get the enum name as a string name() is an inherent method of enums
	
		Animal animal2 = Animal.valueOf("CAT"); //Getting the value of enums
		System.out.println(animal2);
		//Enums are pretty useful for databases!
		
	}

}
