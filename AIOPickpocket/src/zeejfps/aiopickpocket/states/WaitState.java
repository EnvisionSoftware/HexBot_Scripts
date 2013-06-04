package zeejfps.aiopickpocket.states;

import org.hexbot.api.input.Mouse;
import org.hexbot.api.methods.Npcs;
import org.hexbot.api.methods.Players;
import org.hexbot.api.util.Random;
import org.hexbot.api.util.Timer;
import org.hexbot.api.wrapper.Npc;
import org.hexbot.script.Script;

import zeejfps.aiopickpocket.AIOPickpocket;

public class WaitState extends State {

	private Timer t;
	
	public WaitState() {
		super("Waiting");
		t = new Timer(0);
	}

	@Override
	public void doTasks() {
		if (t.getElapsed() > 7000) {
			AIOPickpocket.setState(new InteractState());
		}
		
		if (Players.getLocal().getAnimation() == 881) {
			Script.sleep(Random.nextInt(200, 400));
			AIOPickpocket.setState(new InteractState());
		}
		
		if (Players.getLocal().getAnimation() == 388) {
			Script.sleep(Random.nextInt(1000, 1800));
		}
		
		switch (Random.nextInt(0, 10)) {
			
		case 0: case 1: case 3:
			Npc nextNpc = Npcs.getNearest(AIOPickpocket.getNpcIds());
			if (nextNpc != null && nextNpc.isOnScreen()) {
				Mouse.move(nextNpc.getScreenLocation());
			}
			break;
			
		default:
			break;
		}
		
	}

}
