import javax.swing.SwingUtilities;

/*
 * In a GUI application with MultiThreading, you shouldn't have your UI be updated from threads that you create yourself;
 * it should be updated from the "main" thread.
 * For that reason the SwingWorker class was introduced. With the SwingWorker, you can update UI from its thread.
 * Two reasons from updating the UI:
 * 1. Update status label.
 * 2. Update progress bar.
 * 
 * In many cases you will run into problems if you update the UI from the worker thread. 
 * In this tutorial, we shall use SwingWorker to update the UI, a process which is advisable.
 * 
 * Solution:
 * 1. Use Callable / Future
 * 2. Use SwingWorker
 * 
 * We shall be focusing on Solution 2 in this tutorial.
 */
public class App {

	public static void main(String[] args) {
		// Remember? This is the standard way to start a GUI application
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				new MainFrame("SwingWorker Demo");
				
			}
			
		});
	
	}

}
