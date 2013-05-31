package zeejfps.script.states;

import org.hexbot.api.methods.Calculations;
import org.hexbot.api.methods.Players;
import org.hexbot.api.methods.Skills;
import org.hexbot.api.methods.Walking;
import org.hexbot.api.wrapper.Npc;
import org.hexbot.script.Script;

import zeejfps.script.AIOFighter;
import zeejfps.script.utill.Methods;

public class ReadyState extends State{

	public ReadyState() {
		super("Ready");
	}

	@Override
	public void doTasks() {
		int currentHp = Skills.CONSTITUTION.getCurrentLevel();	
		int maxHp = Skills.CONSTITUTION.getRealLevel();
		
		if (currentHp <= maxHp/2 && Methods.hasFood()) {
			AIOFighter.setState(new EattingState());
		} else {
			
			Npc interactingNpc = (Npc) Players.getLocal().getInteracting();
			Npc npcToAttack = Methods.closestAttackableNPC();
			
			if (interactingNpc == null && npcToAttack != null) {

				if (Calculations.distanceTo(npcToAttack) >= 9 && Calculations.distanceTo(npcToAttack) <= 20) {
					
					Walking.walkTileMM(npcToAttack);
					Script.sleep(500);
				} 
				
				if (Methods.interact("Attack", npcToAttack)) {
					AIOFighter.setState(new FightingState());
				}
	
			} else if (interactingNpc != null && npcToAttack != null && !interactingNpc.equals(npcToAttack)){
				
				if (Methods.interact("Attack", interactingNpc)) {
					AIOFighter.setState(new FightingState());
				}
				
			} 
		}
	}
}
