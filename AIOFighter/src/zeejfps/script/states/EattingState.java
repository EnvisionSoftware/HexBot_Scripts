package zeejfps.script.states;

import java.awt.Point;

import org.hexbot.api.input.Mouse;
import org.hexbot.api.methods.Inventory;
import org.hexbot.api.methods.Skills;
import org.hexbot.api.methods.Tabs;
import org.hexbot.api.methods.Widgets;
import org.hexbot.api.util.Random;
import org.hexbot.api.wrapper.Component;
import org.hexbot.api.wrapper.InventoryItem;
import org.hexbot.api.wrapper.Tab;
import org.hexbot.script.Script;

import zeejfps.script.AIOFighter;
import zeejfps.script.utill.Food;
import zeejfps.script.utill.Methods;

/*
 * This class is responsible for handling how the Player eats, 
 * once this State is called.
 */
public class EattingState extends State{

	public EattingState() {
		super("Eatting");

	}

	@Override
	public void doTasks() {
		
		//Checks to make sure Player has food.
		if (Methods.hasFood()) {	
			//Checks to see if the INVENTORY tab is opened.
			if (Tabs.getCurrent().equals(Tab.INVENTORY)) {
				int currentHp = Skills.CONSTITUTION.getCurrentLevel();	
				int maxHp = Skills.CONSTITUTION.getRealLevel();
				
				for (InventoryItem i : Inventory.getAll()) {
					for (Food f : Food.values()) {
			
						if (f.getId() == i.getId()) {
							i.interact("Eat");
							break;
						}
					}
					Script.sleep(500,700);
					currentHp = Skills.CONSTITUTION.getCurrentLevel();	
					if (currentHp < maxHp) {
						continue;
					} else {
						break;
					}
				}
			//If the INVENTORY tab is not opened, it opens it.
			} else {
				Component inv = Widgets.getChild(548, 50);
				double x = inv.getBounds().getCenterX() + Random.nextInt(-10, 10);
				double y = inv.getBounds().getCenterY() + Random.nextInt(-10, 10);
				
				Mouse.click(new Point((int)x, (int)y));
				Script.sleep(200, 400);
				return;
			}
		}
		//Once its done eatting, set the script to a ready state.
		AIOFighter.setState(new ReadyState());
	}
}
