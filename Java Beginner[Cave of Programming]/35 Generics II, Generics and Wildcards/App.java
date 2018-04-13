import java.util.ArrayList;

// How to use the wildcard with generic parameterized classes
// Quite an advanced topic

class Machine{

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "I am a machine.";
	}
	public void start(){
		System.out.println("Machine started.");
	}
	
}
class Camera extends Machine{
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "I am a camera.";
	}
	public void snap(){
		System.out.println("Photo taken.");
	}

}

public class App {

	public App() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		
		ArrayList<Machine> list2 = new ArrayList<Machine>();
		
		list2.add(new Machine());
		list2.add(new Machine());
		showList(list2);
		
		ArrayList<Camera> list3 = new ArrayList<Camera>();
		
		list3.add(new Camera()); //Note Camera 
		list3.add(new Camera());
		showList2(list3); //Error because ArrayList<Camera> is not a sub class of ArrayList<Machine>, even though Camera is a sub class of Machine
						 //So you can't pass it into showList
		//So what do we do? //
		//We put the wildcard "?" as the parameter in showList's ArrayList
	}
/*
	public static void showList(ArrayList<Machine> list){
		for(Machine value:list){
			System.out.println(value);
		}
	}
	*/
/*	
	public static void showList(ArrayList<?> list){
		for(Object value:list){ //Note type Object. This works because Object is the superclass of both Camera and Machine
			System.out.println(value);

		}
	}
	*/
	
	// Wildcards: Upper and Lower bounds 
	public static void showList(ArrayList<? extends Machine> list){//Upper bound, whatever I pass to the showlist method has to be Machine or lower
		for(Machine value:list){ 
			System.out.println(value);
			value.start();

		}
	}
	
	public static void showList2(ArrayList<? super Camera> list){//Lower bound, whatever I pass to the showlist method has to be Camera or higher
		for(Object value:list){ //can only be type Object
			System.out.println(value);
			((Camera) value).snap();    //If you want to use methods specific to Camera or Machine, you have to know what type of thing was in the ArrayList, and you have to cast up or down accordingly.

		}
	}
	
	
}



