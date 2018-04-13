import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/*
 * Complex data structures are important parts of any programming language
 * Use a complex data structures when you want to hold data in memory but you don't know what data it is.
 * You should always try to define a class to hold some data, instead of using a complex data structure.
 */
public class App {
	
	public static String[] vehicles = {   //Some data
			"ambulance","helicopter","lifeboat"
	};
	
	public static String[][] drivers = {  //Some data
		{"Fred","Sue","Pete"},  //These guys can operate an ambulance
		{"Sue","Richard","Bob","Fred"}, //These guys can operate a helicopter
		{"Pete","Mary","Bob"}, //These guys can operate a lifeboat
		
		/*The idea here is that if someone needs a driver for an ambulance, we go to the appropriate list, 
		 * and take the first guy. If the first guy is unavailable, we take the second guy, and so on.
		 */
	};

	public static void main(String[] args) {
		
		Map<String, Set<String>> personnel =  new HashMap<String,Set<String>>(); //Nesting a data structure inside another
		                                    							      //We need retrieval, so use Map. We do not need order, so we use a simple Map.
		                                    				                  //We cannot have duplicates in the drivers list, so we use a Set. We need it in this order, so we use a LinkedHashSet.
		for(int i = 0;i<vehicles.length;i++){
			
			String vehicle = vehicles[i];
			//System.out.println(vehicle);
			
			String[] driversList = drivers[i];
			
			Set<String> driverSet = new LinkedHashSet<String>();
			
			for(String driver:driversList){   
				driverSet.add(driver);           //Converting from drivers[i] to a LinkedHashSet.
				//System.out.println(driver);
			}
			personnel.put(vehicle, driverSet);
			
		}
		
		
		{ //Brackets just to scope driversList variable so can use again later
			
		//Example usage
		Set<String> driversList = personnel.get("helicopter");
		
		for(String driver:driversList){
			System.out.println(driver);
			
		}
		
		} //Brackets just to scope driversList variable
		
		//Iterate through whole thing
		for(String vehicle:personnel.keySet()){
			System.out.print(vehicle);
			System.out.print(": ");
			Set<String> driversList = personnel.get(vehicle);
			
			for(String driver:driversList){
				System.out.print(driver);
				System.out.print(" ");
				
			}
			System.out.println();
		}

	}

}
