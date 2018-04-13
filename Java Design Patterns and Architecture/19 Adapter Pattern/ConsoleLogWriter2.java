// The Inheritance Method. 
// We have actually seen usage of this in the DAO examples.
// The Adapter Class.
public class ConsoleLogWriter2 extends ConsoleWriter implements LogWriter{

	@Override
	public void out(String text) {
		writeConsole(text);
		
	}

}
