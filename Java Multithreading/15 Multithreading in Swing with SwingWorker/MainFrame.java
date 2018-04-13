import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

public class MainFrame extends JFrame {
	
	private JLabel countLabel1 = new JLabel("0");
	private JLabel statusLabel = new JLabel("Task not completed.");
	private JButton startButton = new JButton("Start");
	
	public MainFrame(String title){ // Constructor for class MainFrame
		super(title);
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		// The Form
		gc.fill = GridBagConstraints.NONE;
		
		//Form for count1
		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		add(countLabel1,gc);
		
		//Form for statusLabel
		gc.gridx = 0;
		gc.gridy = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		add(statusLabel, gc);
		
		//Form for startButton
		gc.gridx = 0;
		gc.gridy = 2;
		gc.weightx = 1;
		gc.weighty = 1;
		add(startButton,gc);
		
		startButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				start();
			}
			
		});
		
		setSize(200,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	private void start(){
		SwingWorker<Boolean, Integer> worker = new SwingWorker<Boolean, Integer>(){  // Void is the class version of the void. Same difference as with Integer and int.
					//This is how you get return values out. 
					// To access the first part, you use get()
					// To access the second, you use publish("whatever") along with the process() method.
			
			@Override
			protected Boolean doInBackground() throws Exception { // the public void run() analog for SwingWorker
				for(int i =0;i<30;i++){
					Thread.sleep(100);
					System.out.println("Hello: " + i);
					
					publish(); // NOTE!
				}
				return true;
			}

			@Override
			protected void done() { //done() is called when the thread finishes. In here you can safely update your UI.
				System.out.println("Done!");
				
				// Updating the Status Label
				try {
					Boolean status = get(); // Yes, just get()
					statusLabel.setText("Completed with status: "+status);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
				
				
				
			}
			
			// Updating the Progress Bar
			@Override // Called automatically in doInBackground()
			protected void process(List<Integer> chunks) { // to be dealt with the second part of the < , >
				int value = chunks.get(chunks.size()-1);
				countLabel1.setText("Current Value: " +value);
				System.out.println("I am being called automatically from publish()!");
			}
			
			
			
			
		};
		
		worker.execute(); // NOT worker.start();
		/*
		 * Work should always be done in the worker thread, NEVER in the main thread, because running work in the main thread will lock up the UI.
		 * Also, SwingWorker is a one-shot thing. Every time you run it, you need to create a new SwingWorker.
		 */
	}

}
