
public abstract class AnimalFactory { //optional to use Abstract --> to make sure ppl don't instantiate this class
	
	public static final int CAT = 0;
	public static final int DOG = 1;
	
	public static Animal createAnimal(int type){ //static because you want to use it via the class. i.e. AnimalFactory.createAnimal() 
		switch(type){
		case CAT:
			return new Cat();
		case DOG:
			return new Dog();
		default:
			return null;
		}
	}

}
