import java.io.Serializable;

/*
 * The Transient keyword is used during serialization.
 */
public class Person implements Serializable { 
	/**
	 * Serialization will work fine without the serialVersionUID,
	 * but what it does is it provides a tool to check that you are using the same version
	 * when you serialize and deserialize the class
	 */
	private static final long serialVersionUID = 4801633306273802062L; //L at the end to cast an integer to type long


	private transient int id;
	private String name;
	//Sometimes you have fields that you don't want to serialize (e.g. threads, locks etc)
	//So you prevent the field from being serialized using the transient keyword
	
	private static int count; //only one int count for all instances
	                          //Doesn't make sense to serialize count
	

	
	public Person(int id, String name) {
		this.id = id;
		this.name = name;
		
	}

	

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + "] Count is: " + count ;
	}



	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		Person.count = count;
	}
	

}
