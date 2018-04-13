class Thing{
	public String name;
	public static String description; // note static! 
	/* Static means that description is a variable to the class Thing 
	 * Each thing1, thing2 have the same description, but different name because name is an instance variable
	 * It also works for subclasses. Look at Derivative class below.
	 * When anyone of them (subclasses or instances) change this static variable, ALL instances of this variable changes.
	 * It's like a local variable - everyone has it, but with a global characteristic - everyone MUST HAVE the same copy.
	 * 
	 */
	
	public final static int LUCKY_NUMBER = 2; //final variables are unchangeable; final + static creates a constant variable
	
	public static int count = 0; //static variables are useful for counting
	
	public int id;
	
	public Thing(){
		this.id = count;
		count++;
	}
	public void showName(){
		System.out.println(name);
		System.out.println("Object ID: "+id);
	}
	public static void showInfo(){ //static methods apply to the whole class
		System.out.println("Hello"); 
		System.out.println(description);//static methods can access static data
		//System.out.println(name); static methods cannot call instance variables like name
	}
	
	
}
class Derivative extends Thing {
	public Derivative() {
		
	}
	public static void showInfo() {
		System.out.println(description);
	}
}
public class App {

	public App() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thing.description="I am a thing.";
		Thing thing1 = new Thing();
		Thing thing2 = new Thing();
		
		thing1.name = "Bob";
		thing2.name = "Sue";
		
		thing1.showName();
		thing2.showName();
		
		thing1.showInfo(); //You can call a static method from both instance and class
		Thing.showInfo();
		
		System.out.println(Math.PI); //PI is a static variable from the Math class
		                             // usually static variables are used for constants
	                  
		System.out.println(Thing.count);
		
		
		Derivative d = new Derivative();
		d.description = "I am a derivative/subclass and I changed a static variable!";
		d.showInfo();
		thing1.showInfo();
		

	}

}
