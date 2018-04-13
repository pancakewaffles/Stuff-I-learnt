
public class Logger {
	
	public LogWriter logWriter; //LogWriter is an interface
	
	public Logger(LogWriter logWriter){ //Truly you can also use a setLogWriter, just like in the DAO examples.
		this.logWriter = logWriter;
	}
	
	public void write(String text){
		logWriter.out(text);
	}
}
