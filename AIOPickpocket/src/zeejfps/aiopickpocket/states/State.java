package zeejfps.aiopickpocket.states;

public abstract class State {
	
	private final String name;
	
	public State(String name){
		this.name = name;
	}
	
	public abstract void doTasks();
	
	public String getName() {
		return name;
	}
}
