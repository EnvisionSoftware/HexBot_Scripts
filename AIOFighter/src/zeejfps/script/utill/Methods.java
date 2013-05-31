package zeejfps.script.utill;

import org.hexbot.api.input.Mouse;
import org.hexbot.api.methods.Calculations;
import org.hexbot.api.methods.Camera;
import org.hexbot.api.methods.Inventory;
import org.hexbot.api.methods.Menu;
import org.hexbot.api.methods.Npcs;
import org.hexbot.api.methods.Players;
import org.hexbot.api.wrapper.Npc;
import org.hexbot.script.Script;

import zeejfps.script.AIOFighter;

/*
 * This class contains Static Methods that are used
 * to enhance the API of the bot.
 */
public abstract class Methods {
	
	private Methods() {} //To prevent instantiation.
	
	/*
	 * This method returns an Npc that is the most
	 * closest and also attackable npc.
	 */
	public static Npc closestAttackableNPC() {
		
		Npc[] allNpcs = Npcs.getLoaded();
		Npc closestNpc = null;
		double dist = 99999;
		
		for (Npc npc : allNpcs) {
			
			for (int i : AIOFighter.getNpcIds()) {
				
				if (npc.getId() == i && npc.getInteracting() == null && npc.getAnimation() == -1) {
					
					double distToNpc = Calculations.distanceTo(npc);
					
					if (distToNpc < dist) {
						closestNpc = npc;
						dist = distToNpc;
						break;
					}
				}
			}
		}//End npc loop	
		return closestNpc;
	}//End closestAttackableNpc method
	
	/*
	 * This class basically enhances the original interact
	 * API method.
	 */
	public static boolean interact(String action, Npc npc) {
		
		for (int i = 0; i < 5; i ++) {
			
			if (npc.isOnScreen()) {
			
				if (Menu.getMenuOptions().contains(action)) {
					Mouse.click(true);
					Script.sleep(1000);
					
					if (Players.getLocal().isMoving()) {
						Script.sleep(1500);
					}
					
					return true;
				} else {
					Mouse.move(npc.getScreenLocation());
				}
				
			} else {
				Camera.turnTo(npc);
			}
			
		}
		return false;
	}//End interact Method
	
	/*
	 * This method checks if the Player has
	 * food in his inventory.
	 */
	public static boolean hasFood() {
		
		for (Food f : Food.values()) {
			
			if (Inventory.contains(f.getId())){
				return true;
			}
			
		}
		return false;
	}//End hasFood Method
	
	/*
	 * This Method will move the mouse to the next 
	 * closestAttackableNpc.
	 */
	public static void moveMouseToNextNpc() {
		Npc nextNpc = Methods.closestAttackableNPC();
		
		if (nextNpc != null) {
			
			if (nextNpc.isOnScreen()) {
				Mouse.move(nextNpc.getLocation().getScreenLocation());
			} else {
				Camera.turnTo(nextNpc);
			}
			
		}
	}
}
