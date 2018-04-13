import java.io.Serializable;

/*
 * Serialization: Turning an Object into binary data
 * Deserialization: Turning a binary data into an Object
 * In this case we turn an object into a file and back again
 */
public class Person implements Serializable { //Making the class serializable!
	/**
	 * 
	 */
	private static final long serialVersionUID = 4801633306273802062L;
	//What is this? Just to prevent some annoying warning. 
	//It is to make sure that the object you read matches the object that you wrote to it.
	


	
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
	

}
