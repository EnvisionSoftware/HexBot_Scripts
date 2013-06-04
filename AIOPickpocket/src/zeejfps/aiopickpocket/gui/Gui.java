package zeejfps.aiopickpocket.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.hexbot.script.Script;

public abstract class Gui implements Runnable {

	private final String title;
	private final Script script;
	
	private boolean activated;
	private JFrame window;
	
	public Gui(String title, Script script) {
		this.title = title;
		this.script = script;
		
		activate();
	}
	
	private void activate() {
		activated = true;
		
		SwingUtilities.invokeLater(this);
		while (activated) {
			Script.sleep(500);
		}
	}
	
	@Override
	public void run() {
		
		window = new JFrame(title);
		window.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				script.log("Gui closed prematurely, stopping script...");
				activated = false;
				script.stop();
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				activated = false;
			}
		});
		
		window.setContentPane(buildGui());
		
		window.pack();
		window.setResizable(false);
		window.setVisible(true);
	}
	
	public abstract JPanel buildGui();
	
	//Getters and Setters
	public JFrame getFrame() {
		return window;
	}
	
	public Script getScript() {
		return script;
	}
}
