
/*
 * Remember our theory of intrinsic locks? They allow threads to wait for each other by holding on and passing a lock that prevents threads from running until they hold onto it?
 * In this tutorial we shall create our own custom locks.
 */
public class App {
	


	public static void main(String[] args) {
		new Worker().main();

	}

}
