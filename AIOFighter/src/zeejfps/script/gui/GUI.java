package zeejfps.script.gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class GUI {
	
	public static final Dimension SIZE = new Dimension(300, 200);
	public static final String NAME = "AIO Fighter V1.0";
	
	private boolean active;
	private JFrame window;
	
	public GUI() {
		
		active = true;
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				createAndShowGUI();
				
			}
		});
	}
	
	private void createAndShowGUI() {
		
		window = new JFrame(GUI.NAME);
		//window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		window.add(new MainPanel(this));
		
		window.pack();
		window.setResizable(false);
		window.setVisible(true);
		
	}
	
	//Ways to check if the GUI is still active
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean b) {
		active = b;
	}
	
	//Getters for the window
	public JFrame getWindow() {
		return window;
	}
}
