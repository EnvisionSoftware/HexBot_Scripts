package zeejfps.aiopickpocket.states;

import org.hexbot.api.input.Mouse;
import org.hexbot.api.methods.Camera;
import org.hexbot.api.methods.Menu;
import org.hexbot.api.methods.Npcs;
import org.hexbot.api.util.Random;
import org.hexbot.api.wrapper.Npc;
import org.hexbot.script.Script;

import zeejfps.aiopickpocket.AIOPickpocket;

public class InteractState extends State {

	public InteractState() {
		super("Interacting");
	}

	@Override
	public void doTasks() {
		
		Npc npcToThieve = Npcs.getNearest(AIOPickpocket.getNpcIds());
		if (interact(npcToThieve)){
			AIOPickpocket.setState(new WaitState());
		}
	}
	
	private boolean interact(Npc npc) {
		for (int i = 0; i < 6; i ++) {
			if (npc != null && npc.isOnScreen()) {
				if (Menu.getMenuOptions().contains("Pickpocket")) {
					Mouse.click(false);
					Script.sleep(Random.nextInt(50, 200));
					Menu.click("Pickpocket");
					moveMouseRandom();
					return true;
				} else {
					int x = npc.getScreenLocation().x + Random.nextInt(-10, 10);
					int y = npc.getScreenLocation().y + Random.nextInt(-10, 10);
					Mouse.move(x, y);
				}
			} else if (npc != null) {
				Camera.turnTo(npc);
			}
		}
		return false;
	}
	
	private void moveMouseRandom() {
		
		switch (Random.nextInt(0, 11)) {
		
		case 0: case 5: case 1: case 6: case 9: case 10: 
		
			int x = Mouse.getX() + Random.nextInt(-30, 10);
			int y = Mouse.getY() + Random.nextInt(-10, 30);
			
			Mouse.move(x, y);
			Script.sleep(Random.nextInt(0, 50));
			break;
			
		default:
			break;
		}
	}
}
