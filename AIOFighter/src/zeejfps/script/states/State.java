package zeejfps.script.states;

public abstract class State {
	
	private final String name;
	
	public State(String name) {
		
		this.name = name;
		
	}
	
	public String getName() {
		return name;
	}
	
	public abstract void doTasks();
	
}