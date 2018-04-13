/*
 * Implementing the Iterable interface in our custom class allows us to use the foreach method on that class
 */
public class App {

	public App() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		UrlLibrary urlLibrary = new UrlLibrary();
		
		for(String html:urlLibrary){ //calls next() from your Iterator
			System.out.println(html.length());
			//System.out.println(html);
		}
	}

}
