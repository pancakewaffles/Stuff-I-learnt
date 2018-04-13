//All classes have Object as their parent
//obj.HashCode() and obj.toString() are methods of the parent class Object

class Frog{
	
	private int id;
	private String name;
	
	public Frog(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	public String toString(){
		
		return String.format("%4d: %s", id,name);
		/*
		StringBuilder sb = new StringBuilder();
		sb.append(id).append(": ").append(name);
		return sb.toString();
		*/
		
		//return "Lex" 
		//return id + ": "+ name;   this is inefficient, so we use StringBuilder
	}
}
public class App {

	public App() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Frog frog1 = new Frog(7,"Freddy");
		Frog frog2 = new Frog(5,"Roger");
		
		System.out.println(frog1); //what System.out.println does is to use the toString() method on the arg
	}

}
