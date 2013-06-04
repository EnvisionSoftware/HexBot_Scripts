package zeejfps.aiopickpocket.utill;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.hexbot.api.input.Mouse;
import org.hexbot.api.methods.Skills;
import org.hexbot.api.util.Timer;

import zeejfps.aiopickpocket.AIOPickpocket;

public class Paint {

	private static final Timer t = new Timer(0);
	private static final int startExp = Skills.THIEVING.getExperience();
	private static final int startLvl = Skills.THIEVING.getCurrentLevel();
	
	public static void draw(Graphics g, BufferedImage img) {
		
		Graphics2D g2d = (Graphics2D)g;
		
		//Drawing the mouse cursor.
		g2d.setColor(Color.CYAN);
		g2d.drawLine(Mouse.getX() - 7, Mouse.getY(), Mouse.getX() + 7, Mouse.getY());
		g2d.drawLine(Mouse.getX(), Mouse.getY() - 7, Mouse.getX(), Mouse.getY() + 7);
		
		int expGained = Skills.THIEVING.getExperience() - startExp;
		int curLvl = Skills.THIEVING.getCurrentLevel();
		int lvlGained = Skills.THIEVING.getCurrentLevel() - startLvl;
		
		//Drawing the Paint.
		if (img != null) {
			g2d.drawImage(img, 4, 244, null);
		}
		
		g2d.setColor(Color.CYAN);	
		
		//Drawing the Time Running:
		if (t != null) { g2d.drawString(t.toElapsedString(), 258, 303);}
		
		//Drawing the State:
		if (AIOPickpocket.getState() != null) {
			g2d.drawString(AIOPickpocket.getState().getName(), 258, 330);
		} else {
			g2d.drawString("SetUp...", 259, 330);
		}
		
		//Drawing Exp Gained:
		g2d.drawString(String.format("%,d", expGained), 424, 303);
		
		//Drawing Lvl and Lvls Gained:
		g2d.drawString(curLvl + "(" + lvlGained + ")", 176, 330);
				
		//Drawing Exp per Hour:
		g2d.drawString(calcExp(), 424, 330);
		
	}
	
	//Needed for paint
	private static String calcExp() {
		double expGained = Skills.THIEVING.getExperience() - startExp;
		double secondsPassed = t.getElapsed() / 1000.0;
		double xpPerSec = expGained / secondsPassed;
		double xpPerHour = xpPerSec * 3600;

		return "" + String.format("%,.0f", xpPerHour);
	}
	
}
