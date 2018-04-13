
public enum Animal {
	CAT("Fergus"), DOG("Fido"), MOUSE("Jerry");
	
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	Animal(String name){
		this.name= name;
	}
	
	
	public String toString(){ //Overriding toString()
		return name;
	}
}
