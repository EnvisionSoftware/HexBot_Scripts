package zeejfps.script.states;

import org.hexbot.api.methods.Calculations;
import org.hexbot.api.methods.Players;
import org.hexbot.api.methods.Skills;
import org.hexbot.api.util.Random;
import org.hexbot.api.wrapper.Npc;

import zeejfps.script.AIOFighter;
import zeejfps.script.utill.Methods;

/*
 * This class is responsible for handling everything that occurs
 * while the Player is inCombat, that's including checking when Player
 * is done fighting.
 */
public class FightingState extends State{

	public FightingState() {
		super("Fighting");
	}

	@Override
	public void doTasks() {
		
		Npc interactingNpc = (Npc) Players.getLocal().getInteracting();
		
		//Checks if the player is done fighting.
		if (interactingNpc == null || (Calculations.distanceTo(interactingNpc) > 1 && !Players.getLocal().isMoving())) {
			AIOFighter.setState(new ReadyState());
		
		} else {
			
			int currentHp = Skills.CONSTITUTION.getCurrentLevel();	
			int maxHp = Skills.CONSTITUTION.getRealLevel();
			
			//Checks if the player needs to eat.
			if (currentHp < maxHp * 0.3 && Methods.hasFood()) {
				AIOFighter.setState(new EattingState());
			} else {
			
				switch(Random.nextInt(0, 30)) {
				
				case 3: case 4: case 15: case 11: case 0:
					Methods.moveMouseToNextNpc();
					break;
					
				default:
					break;
				
				}
				
			}

		}
		
	}//End first IF Statement

}//End FightingState class
