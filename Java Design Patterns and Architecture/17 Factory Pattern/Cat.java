/*
 * Imagine that this class requires lots of configuration before use 
 * e.g. via constructor parameters
 * Then we can simplify this using Factory method.
 */
public class Cat extends AbstractAnimal implements Animal {

	@Override
	public void speak() {
		System.out.println("Meow!");
		
	}



}
