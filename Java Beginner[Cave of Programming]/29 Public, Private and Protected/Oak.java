
public class Oak extends Plant {
	
	public Oak() {
		//this.type = "tree"; won't work because it is private to the Plant class
		this.size="Large"; //protected means you can access it within the same package/same class/subclass
		this.height = 10; // no access modifier
	}

}
