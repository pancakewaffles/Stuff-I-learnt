import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class SwingApp {

	/**
	 * NOT the adapter pattern!!!!!!!
	 */
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		
		frame.setSize(400,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		frame.addWindowListener(new WindowAdapter(){  
			// You could use WindowListener but you would have to define all 7 of the Override methods.
            // WindowAdapter allows you to define the methods you want, and fill the rest with dummy methods.

			@Override
			public void windowOpened(WindowEvent e) {
				System.out.println("Window opened");
			}
			//WindowAdapter does not implement the Adapter Pattern, because it does not match one interface to another; it just provides dummy methods to the other methods that you don't want to override.
			//Was probably written before the Adapter Pattern was named as such.
			
		});

	}

}
