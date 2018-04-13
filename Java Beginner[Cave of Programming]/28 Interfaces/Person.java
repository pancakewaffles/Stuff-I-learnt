
public class Person implements Info {
	
	private String name;
	
	public void greet(){
		System.out.println("Hello!");
	}
	public Person(String name) {
		// TODO Auto-generated constructor stub
		this.name=name;
	}
	@Override
	public void showInfo() {
		// TODO Auto-generated method stub
		System.out.println("Person name is: "+name);
	}

}
