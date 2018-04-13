
public class App {

	public App() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String info = "";
		
		info += "My name is Bob.";
		info += " ";
		info += "I am a builder."; //Very inefficient as you are creating four variables of info; use up a lot of memory
		
		System.out.println(info);
		
		//The efficient method
		
		StringBuilder sb = new StringBuilder("");
		
		sb.append("My name is Sue,");
		sb.append(" ");
		sb.append("I am a lion tamer.");
		
		sb.toString(); // gets the text
		System.out.println(sb.toString());
		
		//Being lazy
		
		StringBuilder s = new StringBuilder();
		s.append("My name is Roger.").append(" ").append("I am a skydiver."); 
		
		System.out.println(s.toString());
		
		//String Buffer is just a thread safe version of String Builder; for multi-threading
		//Same usage as String Buffer
		
		////////////String Formatting////////////////////
		
		System.out.println("Here is some text.\t That was a tab. \nThat was a new line.");
		System.out.print("Without println there is no newline at the end of the text.");
		System.out.println();
		
		System.out.printf("Total cost: %d", 5); //%d is a formatting character, which it replaces with 5
		System.out.println();
		System.out.printf("Total cost: %d; quantity is %d\n", 5,120); //String Fromating allows more control
		System.out.printf("Total cost: %10d; quantity is %d\n", 5,120); // the 10 in %10d specifies the width
		
		for(int i = 0;i<20; i++){
			System.out.printf("%2d: Mom I did it\n", i);
			
			
		}
		
		for(int i = 0;i<20; i++){
			
			System.out.printf("%2d: %s", i,"Some text.\n"); //%s as a string
			
		}
		
		System.out.printf("Total value: %.2f\n",5.6); //floating point value .2 = how many decimal places
	
		System.out.printf("Total value: %10.2f\n",5.6); //10 is the width

	}

}
