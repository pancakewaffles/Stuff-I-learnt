
class Plant{
	private String name;
	//The idea behind encapsulation is that you encapsulate the inner workings of that class within that class
	//and you expose some public API for ppl to use
	//You can access these private name using Getters and Setters
	
	//A golden rule is to use private for all variables; public for methods
	
	//Advantages: Reduce clutter in your code
	//            Avoid getting entangled with many classes using each other's variables
	
	public static final int ID = 7;
	
	public String getData(){
		String data = "some Stuff " + calculateGrowthForecast();
		return data;
	}
	
	private int calculateGrowthForecast(){
		return 9;
	}
	
}

public class App {

	public App() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
