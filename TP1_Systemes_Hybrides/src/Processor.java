import java.util.ArrayList;
import java.util.List;

public class Processor extends AtomicComposant {

	public Processor(List inputs, List outputs) {
		super(inputs, outputs, null);
		
		List<Double> states = new ArrayList<>() ;
		states.add(Double.POSITIVE_INFINITY) ;
		states.add(3.0) ;
		this.states = states ;
	}

	@Override
	public void deltaExt() {
		if (this.inputs.get((int) this.global_time).equals("req") && this.current_state == 0) {
			this.current_state = 1 ;
		}
	}

	@Override
	protected void deltaInt() {
		if (this.current_state == 2 && this.time_spent == this.ta(this.current_state)) {
			this.current_state = 0 ;
		}

	}

	@Override
	public List<String> lambda() {
		if (this.current_state == 1 && this.tr == 0) {
			return this.outputs ;
		}
		return null;
	}

}
