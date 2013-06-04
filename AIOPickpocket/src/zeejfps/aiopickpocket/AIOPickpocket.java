package zeejfps.aiopickpocket;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;

import org.hexbot.api.listeners.Paintable;
import org.hexbot.api.methods.Skills;
import org.hexbot.api.methods.Widgets;
import org.hexbot.script.Manifest;
import org.hexbot.script.Script;

import zeejfps.aiopickpocket.gui.AIOPickpocketGui;
import zeejfps.aiopickpocket.states.EattingState;
import zeejfps.aiopickpocket.states.InteractState;
import zeejfps.aiopickpocket.states.State;
import zeejfps.aiopickpocket.utill.Methods;
import zeejfps.aiopickpocket.utill.Paint;

@Manifest(author = "Zeejfps", name = "AIOThiever")
public class AIOPickpocket extends Script implements Paintable {

	private static int[] npcIds;
	private static State state;
	private static BufferedImage img;
	
	@Override
	public void onStart() {

		//Setup the Paint.
		try {
			img = ImageIO.read(new URL("http://i699.photobucket.com/albums/vv356/zeejfps/AIOPickpocketPaint_zpsaf1eb980.png"));
		} catch (Exception e) {
			log("Paint could not be found!");
		}
		
		new AIOPickpocketGui(this);
		state = new InteractState();
	}
	
	@Override
	public int loop() {
		
		if (Widgets.canClickContinue()) {
			Widgets.clickContinue();
		}
		
		int currentHp = Skills.CONSTITUTION.getCurrentLevel();	
		int maxHp = Skills.CONSTITUTION.getRealLevel();
		
		//Checks if the player needs to eat.
		if (currentHp < maxHp * 0.5 && Methods.hasFood()) {
			state = new EattingState();
		} 
		
		state.doTasks();
		
		return 100;
	}

	@Override
	public void onEnd() {
		log ("Script Stopped.");
	}
	
	@Override
	public void paint(Graphics g) {
		
		Paint.draw(g, img);
	}
	
	public static void setNpcIds(int[] ids) {
		AIOPickpocket.npcIds = ids;
	}
	public static int[] getNpcIds(){
		return AIOPickpocket.npcIds;
	}
	
	public static void setState(State state) {
		AIOPickpocket.state = state;
	}
	public static State getState() {
		return AIOPickpocket.state;
	}
	
}//End AIOThiever CLASS
