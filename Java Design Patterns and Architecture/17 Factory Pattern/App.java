/*
 * Finally! Had enough of that MVC bs? 
 * This is a new Pattern that we will be learning.
 * Factory Pattern (aka Factory Methods)
 * A Factory is an abstraction of a Constructor.
 * Used when you want to create lots of objects that all implement the same interface.
 * A Factory that churns out objects.
 * 
 * Recall the DAOFactory? MySQLDAOFactory? Yea.
 * 
 * This is an important pattern.
 * It allows you to deal with and simplify choice of objects.
 * Real world examples: DAOFactory, Swing's BorderFactory
 * 
 * Of course you can always use constructors i.e. new Cat() new Dog()
 * but doing it via the Factory method is neater.
 * Also, if you don't know what object to instantiate, a factory can be quite useful. You only need to specify the type, and
 * don't need to "new Cat()"
 * 
 * 
 * 
 */

public class App {

	public static void main(String[] args) {
		
		Animal animal = AnimalFactory.createAnimal(AnimalFactory.CAT);
		
		animal.speak();
		
		animal.eat();
	}

}
