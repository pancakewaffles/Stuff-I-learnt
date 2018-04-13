
public class Plant {
	public String name; // public means you can access it anywhere
	                    // this is usually a bad practice, you want to hide variables and let people access them through methods instead
	public final static int ID=8; // Usually for public variables they are usually some constant
	
	private String type;// stop it from access it from outside the class; you can only access it from within this class
	
	protected String size; // private + access from child class
	
	int height; // no access modifier i.e. no public/private/protected default to package level visibility
	public Plant() {
		name = "Freddy";
		//or this.name = "Freddy"; 
		this.type = "plant";
		this.size = "Medium";
		this.height = 8;
	}

}
