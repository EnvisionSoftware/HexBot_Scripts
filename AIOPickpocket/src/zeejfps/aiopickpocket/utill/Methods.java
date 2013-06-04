package zeejfps.aiopickpocket.utill;

import org.hexbot.api.methods.Inventory;

/*
 * This class contains Static Methods that are used
 * to enhance the API of the bot.
 */
public abstract class Methods {
	
	private Methods() {} //To prevent instantiation.
	
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

}
