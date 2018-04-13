
class Person {
	private int id;
	private String name;

	public Person(int id, String name) {

		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}

public class App {

	public static void main(String[] args) {
		
		System.out.println(new Object()); //java.lang.Object@15db9742 --> 15db9742 = hashCode, a unique ID.
		
		Person person1 = new Person(5,"Bob");
		Person person2 = new Person(5,"Bob");
		
		//person2 = person1; //Putting the reference of person2 to person1
		
		//Are these two people equal?
		
		System.out.println(person1 == person2); // == tells you whether both references are pointing to the same Object
		
		System.out.println(person1.equals(person2)); //We shall override .equal and implement the method ourself
		//Voila! We made .equals accept the comparison via names
		
		
		Double value1 = 7.2;
		Double value2 = 7.2;
		
		System.out.println(value1 == value2); //lol it's false???
		System.out.println(value1.equals(value2)); //Done! It's true.
		
		Integer number1 = 6;
		Integer number2 = 6;
		
		System.out.println(number1 == number2); //Both references are pointing to the same object - the number 6.
		
		String text1 = "Hello";
		String text2 = "Hello";
		
		System.out.println(text1==text2);  //Works because Java optimized both to point to same object
		
		//== only checks whether both references are pointing at the same object
		//Always use .equals to compare non-primitive numbers and strings
		
		String text3 = "Hello";
		String text4 = "Hellofff".substring(0, 5);
		
		System.out.println(text3==text4);
		System.out.println(text3.equals(text4));
		
	}

}
