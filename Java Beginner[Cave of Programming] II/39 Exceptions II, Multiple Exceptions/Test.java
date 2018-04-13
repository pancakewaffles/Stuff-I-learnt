import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}
	public void run() throws IOException, ParseException{ //Throwing Multiple Exceptions
		
		//throw new IOException(); //To generate Exceptions
		
		throw new ParseException("Error in command list.",2);
	}
	
	public void input() throws IOException, FileNotFoundException{
		
	}
}
