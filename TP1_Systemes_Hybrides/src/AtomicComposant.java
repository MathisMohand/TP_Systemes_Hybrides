import java.util.List;
import java.util.Set;

public abstract class AtomicComposant {

	protected List<String> inputs ;
	protected List<String> outputs ;
	protected List<Double> states ;
	protected int current_state = 0 ;
	protected double time_spent ;
	protected double tr ;
	protected double global_time ;
	
	public AtomicComposant(List inputs, List outputs, List states) {
		this.inputs = inputs ;
		this.outputs = outputs ;
		this.states = states ;
		this.time_spent = 0.0 ;
		this.tr = this.ta(0) ;
		this.global_time = 0.0 ;
		
		this.reset() ;
	}
	
	public abstract void deltaExt() ;
	
	protected abstract void deltaInt() ;
	
	public abstract List<String> lambda() ;
	
	public Double ta(int state) {
		return this.states.get(state) ;
	}
	
	public void reset() {
		this.current_state = 0 ;
	}
	
	public void incrementTime() {
		this.time_spent ++ ; 
		this.tr -- ;
		this.global_time ++ ;
	}
	
	public void addInput(String in) {
		this.inputs.add(in) ;
	}
}
