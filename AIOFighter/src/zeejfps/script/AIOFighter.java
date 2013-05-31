package zeejfps.script;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;

import org.hexbot.api.input.Mouse;
import org.hexbot.api.input.Mouse.Speed;
import org.hexbot.api.listeners.Paintable;
import org.hexbot.api.methods.Camera;
import org.hexbot.api.methods.Widgets;
import org.hexbot.api.util.Timer;
import org.hexbot.script.Manifest;
import org.hexbot.script.Script;

import zeejfps.script.gui.GUI;
import zeejfps.script.states.ReadyState;
import zeejfps.script.states.State;

@Manifest(author = "Zeejfps", name = "AIO Fighter", description = "--> Kills Anything.\n--> Eats when less than 50% health\n", version = 1.1)
public class AIOFighter extends Script implements Paintable {
	
	private static int[] npcIds;
	private static State state;
	private static BufferedImage img;

	private final Timer t = new Timer(0);
//----------------------------------------------Main Stuff Below----------------------------------------\\	
	@Override
	public void onStart() {
		
		//Setup the Paint.
		try {
			img = ImageIO.read(new URL("http://i699.photobucket.com/albums/vv356/zeejfps/paint_zpsef3aeb17.png"));
		} catch (Exception e) {
			log("Paint could not be found!");
		}

		//Creating GUI
		log ("Starting GUI...");
		GUI gui = new GUI();
			while(gui.isActive()) {
				sleep(500);
			}
		gui.getWindow().dispose();
		log ("Finished with GUI, starting Script...");
	
		state = new ReadyState();
		Mouse.setSpeed(Speed.NORMAL);
		Camera.setPitch(true);
		
		log ("Script started!");
		
	}
	
	@Override
	public int loop() {
		
		if (Widgets.canClickContinue()) {
			Widgets.clickContinue();
		}
		
		//log(state.getName());
		state.doTasks();

		return 100;
	}
	
	@Override
	public void onEnd() {		
		img = null;
		log ("Script Stopped.");
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		//Drawing the paint
		if (img != null) {
			g2d.drawImage(img, 4, 244, null);
		}

		g2d.setColor(Color.CYAN);	
		//Drawing the Time Running:
		if (t != null) { g2d.drawString(t.toElapsedString(), 263, 303);}
		//Drawing the State:
		if (state != null) {g2d.drawString(state.getName(), 263, 330);} else {g2d.drawString("SetUp...", 263, 330);}
		//Drawing Lvl
		
	
		//Drawing the mouse cursor
		g2d.setColor(Color.CYAN);
		g2d.drawLine(Mouse.getX() - 7, Mouse.getY(), Mouse.getX() + 7, Mouse.getY());
		g2d.drawLine(Mouse.getX(), Mouse.getY() - 7, Mouse.getX(), Mouse.getY() + 7);
	}
//----------------------------------------------Main Stuff Above----------------------------------------\\

	//State getters and setters
	public static void setState(State state) {
		AIOFighter.state = state;	
	}
	public static State getState() {
		return AIOFighter.state;
	}
	
	//NpcIds getters and setters.
	public static void setNpcIds(int[] npcIds) {
		AIOFighter.npcIds = npcIds;		
	}
	public static int[] getNpcIds() {	
		return AIOFighter.npcIds;	
	}
	
}
