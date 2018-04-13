
public class App {

	public App() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		byte byteValue = 20; //stores from -128 to 127; total 256 values
		short shortValue = 55; // 16 bits
		int intValue = 888; //32 bits
		long longValue = 2222222; // really long value 64 bits
		
		float floatValue = 99.3f; // 99.3 makes it automatically a double 
		double doubleValue = 32.4534563453; //greater precision than float
		
		System.out.println(Double.MAX_VALUE);
		System.out.println(Byte.MAX_VALUE);
		
		//Casting; i.e. converting from one type to another
		
		intValue = (int)longValue; //Casting
		System.out.println(intValue);
		
		//Casting requires knowledge of the various different types of values
		//Obviously you can't cast a string-word to an integer
		
		intValue = (int)floatValue;
		System.out.println(intValue);
		
		byteValue = (byte)128; //Too big for byte! But check out what it does
		System.out.println(byteValue); // it outputs -127, basically it circles back around
		
	}

}
